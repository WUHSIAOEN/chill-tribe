package core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public class GetSecretValue {

	public static void main(String[] args) {
		final String usage = """

				Usage:
				    <secretName>\s

				Where:
				    secretName - The name of the secret (for example, tutorials/MyFirstSecret).\s
				""";

		if (args.length != 1) {
			System.out.println(usage);
			System.exit(1);
		}

		String secretName = args[0];
		Region region = Region.AP_NORTHEAST_1;
		SecretsManagerClient secretsClient = SecretsManagerClient.builder().region(region).build();

		getValue(secretsClient, secretName);
		secretsClient.close();
	}

	public static String getValue(SecretsManagerClient secretsClient, String secretName) {
		try {
			GetSecretValueRequest valueRequest = GetSecretValueRequest.builder().secretId(secretName).build();

			GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
			String secret = valueResponse.secretString();
//            System.out.println(secret);
			return secret;
		} catch (SecretsManagerException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
			return null;
		}
	}
	
	// 直接定義一個包含Client 的靜態方法
	public static String getOAuthKeyValue(String key) {
		String secretName = "crystal/google";
		Region region = Region.AP_NORTHEAST_1;
		SecretsManagerClient secretsClient = SecretsManagerClient.builder().region(region).build();

		String ClientId = GetSecretValue.getValue(secretsClient, secretName);
		secretsClient.close();
//		System.out.println(ClientId);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper.readTree(ClientId);
			String value = rootNode.get(key).asText();
			return value;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
