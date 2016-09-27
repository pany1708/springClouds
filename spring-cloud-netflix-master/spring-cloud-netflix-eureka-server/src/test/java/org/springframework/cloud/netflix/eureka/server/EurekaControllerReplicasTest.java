package org.springframework.cloud.netflix.eureka.server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.netflix.eureka.server.EurekaControllerTest.setInstance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.eureka.cluster.PeerEurekaNode;
import com.netflix.eureka.cluster.PeerEurekaNodes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.netflix.eureka.util.StatusInfo;


public class EurekaControllerReplicasTest {

	String noAuthList1 = "http://test1.com";
	String noAuthList2 = noAuthList1+",http://test2.com";

	String authList1 = "http://user:pwd@test1.com";
	String authList2 = authList1+",http://user2:pwd2@test2.com";
	
	String empty = new String();

	private ApplicationInfoManager original;

	@Before
	public void setup() throws Exception {
		this.original = ApplicationInfoManager.getInstance();
		setInstance(mock(ApplicationInfoManager.class));
	}

	@After
	public void teardown() throws Exception {
		setInstance(this.original);
	}
	
	@Test
	public void testFilterReplicasNoAuth() throws Exception {
		Map<String, Object> model=new HashMap<String, Object>();
		StatusInfo statusInfo = StatusInfo.Builder.newBuilder().
									add("registered-replicas", empty).
									add("available-replicas",noAuthList1).
									add("unavailable-replicas",noAuthList2).
									build();
		EurekaController controller = new EurekaController(null);
		
		controller.filterReplicas(model,statusInfo);

		@SuppressWarnings("unchecked")
		Map<String,String> results = (Map<String, String>) model.get("applicationStats");
		assertEquals(empty,results.get("registered-replicas"));
		assertEquals(noAuthList1,results.get("available-replicas"));
		assertEquals(noAuthList2,results.get("unavailable-replicas"));
		
	}

	@Test
	public void testFilterReplicasAuth() throws Exception {
		Map<String, Object> model=new HashMap<String, Object>();
		StatusInfo statusInfo = StatusInfo.Builder.newBuilder().
									add("registered-replicas", authList2).
									add("available-replicas",authList1).
									add("unavailable-replicas",empty).
									build();
		EurekaController controller = new EurekaController(null);
		
		controller.filterReplicas(model,statusInfo);
		
		@SuppressWarnings("unchecked")
		Map<String,String> results = (Map<String, String>) model.get("applicationStats");
		assertEquals(empty,results.get("unavailable-replicas"));		
		assertEquals(noAuthList1,results.get("available-replicas"));
		assertEquals(noAuthList2,results.get("registered-replicas"));
		
	}
	
}
