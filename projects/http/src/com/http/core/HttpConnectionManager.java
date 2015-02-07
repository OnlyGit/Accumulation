package com.http.core;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

/**
 * httpClient 管理
 * @author qjf
 */
public class HttpConnectionManager {

	 /**
     * http线程池管理器
     */
	public static PoolingClientConnectionManager conMgr;
	
	/** 
     * 连接池里的最大连接数
     */  
    public static final int MAX_TOTAL_CONNECTIONS = 100;
    
    /** 
     * 每个路由的默认最大连接数
     */  
    public static final int MAX_ROUTE_CONNECTIONS = 50;
    
    /** 
     * 连接超时时间
     */  
    public static final int CONNECT_TIMEOUT = 50000;
    
    /**
     * 套接字超时时间
     */
    public static final int SOCKET_TIMEOUT = 50000;
    
    /**
     * 连接池中 连接请求执行被阻塞的超时时间
     */
    public static final long CONN_MANAGER_TIMEOUT = 60000;
    
    /**
     * http连接相关参数
     */
    private static HttpParams params;
    
    /**
     * 初始化管理池
     */
	private static void initManager() {
		
		conMgr = new PoolingClientConnectionManager();
		conMgr.setMaxTotal(200);
		conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());
		
		params = new BasicHttpParams(); 
		
		params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
		params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIMEOUT);
		params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
		//在提交请求之前 测试连接是否可用
		params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
	}
	
	/**
	 * 通过池获取请求
	 * @return
	 */
	public static DefaultHttpClient getHttpClient() {
		initManager();
		DefaultHttpClient httpClient = new DefaultHttpClient(conMgr, params);
		return httpClient;
	}
	
	/**
	 * 获取单个请求
	 * @return
	 */
	public static DefaultHttpClient getSingleHttpClient() {
		return new DefaultHttpClient();
	}
}
