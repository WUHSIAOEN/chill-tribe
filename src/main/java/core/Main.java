package core;

import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.util.GetSecretValue;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import web.activity.dao.ActivitySearchDao;
import web.activity.dao.impl.ActivitySearchDaoImpl;
import web.activity.service.impl.ActivityServiceImpl2;
import web.activity.vo.Activities;
import web.activity.vo.Activity;
import web.activity.vo.ActivityImage;
import web.order.dao.OrderDao;
import web.order.dao.impl.OrderDaoImpl;
import web.order.vo.Order;
import web.order.vo.Orders;

public class Main {

	public static void main(String[] args) throws NamingException {

		String clientId = GetSecretValue.getOAuthKeyValue("CLIENT_ID");
		System.out.println(clientId);

	}
}
