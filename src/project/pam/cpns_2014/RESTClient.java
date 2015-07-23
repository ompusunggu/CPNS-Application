package project.pam.cpns_2014;

//import javax.security.auth.callback.Callback;
//import com.squareup.okhttp.OkHttpClient;
import retrofit.RestAdapter;
//import retrofit.client.OkClient;
//import retrofit.client.Response;

public class RESTClient {
	private static API_CPNS REST_CLIENT;
	private static String URL = "http://freebus.sayacari.info/api/cpns2014";
	
	static {
		setupRestClient();
	}
	
	private RESTClient(){}
	
	public static API_CPNS get(){
		return REST_CLIENT;
	}
	
	private static void setupRestClient(){
		//RestAdapter builder = new RestAdapter.Builder().setEndpoint(URL).setClient(new OkClient(new OkHttpClient())).build();
		//.setEndpoint(URL).build();
		//builder.setLogLevel(RestAdapter.LogLevel.FULL);
		RestAdapter builder = new RestAdapter.Builder().setEndpoint(URL).build();
		REST_CLIENT = builder.create(API_CPNS.class);		
	}
}

