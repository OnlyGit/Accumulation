package com.wechat.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.bean.MsgType;
import com.wechat.bean.Music;
import com.wechat.bean.MusicMsg;
import com.wechat.bean.ReceiveMsg;
import com.wechat.bean.SendMsg;
import com.wechat.bean.WechatMsg;
import com.wechat.util.ConfigurationUtil;

public class WechatServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String token = ConfigurationUtil.getConfig("token");
	
	public void init() throws ServletException {
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doService2(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doService2(req, resp);
	}

	/**
	 * 密文
	 * @param req
	 * @param resp
	 */
	private void doService2(HttpServletRequest req, HttpServletResponse resp) {
		 // 微信加密签名
        String signature = req.getParameter("signature");
        // 随机字符串
//        String echostr = req.getParameter("echostr");
        // 时间戳
        String timestamp = req.getParameter("timestamp");
        // 随机数
        String nonce = req.getParameter("nonce");
        
        String[] str = {token, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        
        try {
			
	        PrintWriter out = resp.getWriter();
	        
	        ReceiveMsg receiveMsg = XmlUtil.parseXml(req.getInputStream(), ReceiveMsg.class);
//	        System.out.println("密文："+receiveMsg.getEncrypt());
//	        System.out.println("公众号id:"+receiveMsg.getToUserName());
	        String temp = SHA.encode(str[0]+str[1]+str[2]);  
	        if (temp.equals(signature)) {
	        	System.out.println("ok----------");
	        	resp.setContentType("text/xml;charset=UTF-8");
	//        	resp.setCharacterEncoding("UTF-8");
	        	
	        	WechatMsg msg = this.getMusicMsg(receiveMsg);
	        	
	        	String result = XmlUtil.getXml(msg);
	        	System.out.println(result);
	        	out.print(new String(result.getBytes("utf-8"),"iso-8859-1"));
	        }
        } catch (Exception e) {
        	e.printStackTrace();
		}
	}
	/**
	 * 明文
	 * @param req
	 * @param resp
	 */
	private void doService(HttpServletRequest req, HttpServletResponse resp){
		 // 微信加密签名
        String signature = req.getParameter("signature");
        // 随机字符串
//        String echostr = req.getParameter("echostr");
        // 时间戳
        String timestamp = req.getParameter("timestamp");
        // 随机数
        String nonce = req.getParameter("nonce");
        
        String[] str = {token, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        
        try {
			
	        PrintWriter out = resp.getWriter();
//	        out.print(echostr);
	        
	        
	        ReceiveMsg receiveMsg = XmlUtil.parseXml(req.getInputStream(), ReceiveMsg.class);
	        String temp = SHA.encode(str[0]+str[1]+str[2]);  
	        if (temp.equals(signature)) {
	        	System.out.println("ok----------");
	        	resp.setContentType("text/xml;charset=UTF-8");
	//        	resp.setCharacterEncoding("UTF-8");
	        	SendMsg sendMsg = new SendMsg();
	        	sendMsg.setContent("你好,欢迎关注微信好wp8....<>=");
	        	sendMsg.setCreateTime(String.valueOf(System.currentTimeMillis()));
	        	sendMsg.setFromUserName(receiveMsg.getToUserName());
	        	sendMsg.setToUserName(receiveMsg.getFromUserName());
	        	sendMsg.setMsgType(MsgType.TEXT.getType());
	        	
//	        	String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml>" +
//	        			"<ToUserName>oaM5Xt1uuFgV1UYthxenfSGtFUvg</ToUserName>" +
//	        			"<FromUserName>gh_9030cb144064</FromUserName>" +
//	        			"<CreateTime>"+System.currentTimeMillis()+"</CreateTime>" +
//						"<MsgType>text</MsgType>" +
//						"<Content>你好,欢迎关注微信好wp8</Content>" +
//						"</xml>";
	        	
	        	String s1 = XmlUtil.getXml(sendMsg);
	        	out.print(new String(s1.getBytes("utf-8"),"iso-8859-1"));
	        }
        } catch (Exception e) {
        	e.printStackTrace();
		}
	}
	
	private WechatMsg getTextMsg(ReceiveMsg receiveMsg) throws Exception {
		SendMsg sendMsg = new SendMsg();
    	sendMsg.setContent("你好,欢迎关注微信好wp8....<>=");
    	sendMsg.setCreateTime(String.valueOf(System.currentTimeMillis()));
    	sendMsg.setFromUserName(receiveMsg.getToUserName());
    	sendMsg.setToUserName(receiveMsg.getFromUserName());
    	sendMsg.setMsgType(MsgType.TEXT.getType());
    	
    	return sendMsg;
	}
	/**
	 * 获取音乐回复
	 * @param receiveMsg
	 * @return
	 */
	private WechatMsg getMusicMsg(ReceiveMsg receiveMsg) throws Exception {
		MusicMsg msg = new MusicMsg();
		Music music = new Music();
		
		msg.setCreateTime(String.valueOf(System.currentTimeMillis()));
//		msg.setEncrypt("密文");
		msg.setFromUserName(receiveMsg.getToUserName());
		msg.setToUserName(receiveMsg.getFromUserName());
		msg.setMsgType(MsgType.MUSIC.getType());
		msg.setMusic(music);
		
		music.setDescription("苍茫的天涯是我的爱");
		music.setHQMusicUrl("http://zhangmenshiting.baidu.com/data2/music/69553401/69553401.mp3?xcode=5aab3e3496d99d5364063d8a20b5e3c6968e90b583f19a5d");
		music.setMusicUrl("http://zhangmenshiting.baidu.com/data2/music/69553401/69553401.mp3?xcode=5aab3e3496d99d5364063d8a20b5e3c6968e90b583f19a5d");
		music.setTitle("最炫民族风");
//		music.setThumbMediaId("12");
    	
    	return msg;
	}
	
	
	private void test(HttpServletRequest req, HttpServletResponse resp) {
//		resp.setContentType("text/xml;charset=UTF-8");
//		resp.getWriter().print("你好");
//		resp.getWriter().print("<xml>" +
//    			"<ToUserName><![CDATA[oaM5Xt1uuFgV1UYthxenfSGtFUvg]]></ToUserName>" +
//    			"<FromUserName><![CDATA[gh_9030cb144064]]></FromUserName>" +
//    			"<CreateTime><![CDATA["+System.currentTimeMillis()+"]]></CreateTime>" +
//				"<MsgType><![CDATA[text]]></MsgType>" +
//				"<Content><![CDATA[你好？]]></Content>" +
//				"</xml>");
//		System.out.println("<xml>" +
//    			"<ToUserName><![CDATA[oaM5Xt1uuFgV1UYthxenfSGtFUvg]]></ToUserName>" +
//    			"<FromUserName><![CDATA[gh_9030cb144064]]></FromUserName>" +
//    			"<CreateTime><![CDATA["+System.currentTimeMillis()+"]]></CreateTime>" +
//				"<MsgType><![CDATA[text]]></MsgType>" +
//				"<Content><![CDATA[你好？]]></Content>" +
//				"</xml>");
	}
	
}
