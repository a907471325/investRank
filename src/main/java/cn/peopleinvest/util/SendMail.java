package cn.peopleinvest.util;

import cn.peopleinvest.model.ResponseData;
import cn.peopleinvest.model.MailAddressReceiver;
import cn.peopleinvest.model.MailBody;
import cn.peopleinvest.model.SendCloudMail;
import cn.peopleinvest.model.TextContent;
import cn.peopleinvest.builder.SendCloudBuilder;
import cn.peopleinvest.core.SendCloud;
import cn.peopleinvest.model.*;
import cn.peopleinvest.model.TextContent.ScContentType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendMail {

	public static void send_common() throws Throwable {
		MailAddressReceiver receiver = new MailAddressReceiver();
		// 添加收件人
		receiver.addTo("a@ifaxin.com;b@ifaxin.com");
		// 添加抄送
		//receiver.addCc("c@ifaxin.com");
		// 添加密送
		//receiver.addBcc("d@ifaxin.com");

		MailBody body = new MailBody();
		// 设置 From
		body.setFrom("isflag@126.com");
		// 设置 FromName
		body.setFromName("investRank");
		// 设置 ReplyTo
		//body.setReplyTo("907471325@qq.com");

		List<String> list =  new ArrayList<String>();
		list.add("907471325@qq.com");
		body.addXsmtpapi("to", list);
		// 设置标题
		body.setSubject("欢迎加入投榜");
		// 创建文件附件
		//body.addAttachments(new File("D:/1.png"));
		//body.addAttachments(new File("D:/2.png"));
		//// 创建流附件
		// body.addAttachments(new FileInputStream(new File("D:/ff.png")));

		TextContent content = new TextContent();
		content.setContent_type(ScContentType.html);
		StringBuffer sb=new StringBuffer("加入投榜的最后一步</br>");
		sb.append("<a href=\"http://192.168.181.195:8080/regist?email=");
		//sb.append(loginuser.getLoginname());
		sb.append("&token=");
		//sb.append(loginuser.getRemark());
		sb.append("\">http://192.168.181.195:8080/regist?email=");
		//sb.append(loginuser.getLoginname());
		sb.append("&token=");
		//sb.append(loginuser.getRemark());
		sb.append("</a>");
		content.setText(sb.toString());

		SendCloudMail mail = new SendCloudMail();
		mail.setTo(receiver);
		mail.setBody(body);
		mail.setContent(content);

		SendCloud sc = SendCloudBuilder.build();
		ResponseData res = sc.sendMail(mail);
		System.out.println(res.getResult());
		System.out.println(res.getStatusCode());
		System.out.println(res.getMessage());
		System.out.println(res.getInfo());
	}

	public static void send_common_advanced() throws Throwable {
		MailAddressReceiver receiver = new MailAddressReceiver();
		// 添加收件人
		receiver.addTo("a@ifaxin.com");
		// 添加抄送
		receiver.addCc("b@ifaxin.com");
		// 添加密送
		receiver.addBcc("c@ifaxin.com");

		MailBody body = new MailBody();
		// 设置 From
		body.setFrom("sendcloud@sendcloud.org");
		// 设置 FromName
		body.setFromName("SendCloud");
		// 设置 ReplyTo
		body.setReplyTo("reply@sendcloud.org");
		// 设置标题
		body.setSubject("来自 SendCloud SDK 的邮件");
		// 创建文件附件
		//body.addAttachments(new File("D:/1.png"));

		// 配置 Xsmtpapi 扩展字段
		List<String> toList = new ArrayList<String>();
		toList.add("d@ifaxin.com");
		toList.add("e@ifaxin.com");
		List<String> moneyList = new ArrayList<String>();
		moneyList.add("1000");
		moneyList.add("2000");
		List<String> nameList = new ArrayList<String>();
		nameList.add("a");
		nameList.add("b");
		Map<String, List<String>> sub = new HashMap<String, List<String>>();
		sub.put("%name%", nameList);
		sub.put("%money%", moneyList);
		// 此时, receiver 中添加的 to, cc, bcc 均会失效
		body.addXsmtpapi("to", toList);
		body.addXsmtpapi("sub", sub);
		body.addHeader("SC-Custom-test_key1", "test1");
		body.addHeader("NO-SC-Custom-test_key1", "test2");

		TextContent content = new TextContent();
		content.setContent_type(ScContentType.html);
		content.setText("<html><p>亲爱的 %name%: </p> 您本月的支出为: %money% 元.</p></html>");

		SendCloudMail mail = new SendCloudMail();
		mail.setTo(receiver);
		mail.setBody(body);
		mail.setContent(content);

		SendCloud sc = SendCloudBuilder.build();
		ResponseData res = sc.sendMail(mail);
		System.out.println(res.getResult());
		System.out.println(res.getStatusCode());
		System.out.println(res.getMessage());
		System.out.println(res.getInfo());
	}

	public static void send_template(String toEmail,String toUrl) throws Throwable {
		MailBody body = new MailBody();
		body.setFrom("isflag@162.com");

		body.setFromName("admin");

		body.setSubject("请激活您的投榜邮箱");


		List<String> toList = new ArrayList<String>();
		toList.add(toEmail);

		List<String> url = new ArrayList<String>();
		url.add(toUrl);

		Map<String, List<String>> sub = new HashMap<String, List<String>>();
		sub.put("%url%", url);
		// 此时, receiver 中添加的 to, cc, bcc 均会失效
		body.addXsmtpapi("to", toList);
		body.addXsmtpapi("sub", sub);
		body.addHeader("SC-Custom-test_key1", "test1");
		body.addHeader("NO-SC-Custom-test_key1", "test2");

		// 使用邮件模板
		TemplateContent content = new TemplateContent();
		content.setTemplateInvokeName("test_template_active");

		SendCloudMail mail = new SendCloudMail();
		// 模板发送时, 必须使用 Xsmtpapi 来指明收件人; mail.setTo();
		mail.setBody(body);
		mail.setContent(content);

		SendCloud sc = SendCloudBuilder.build();
		ResponseData res = sc.sendMail(mail);
	}

	public static void send_with_addresslist() throws Throwable {
		AddressListReceiver receiver = new AddressListReceiver();
		// 设置地址列表
		receiver.addTo("liubidatest@maillist.sendcloud.org");

		MailBody body = new MailBody();
		// 设置 From
		body.setFrom("sendcloud@sendcloud.org");
		// 设置 FromName
		body.setFromName("SendCloud");
		// 设置 ReplyTo
		body.setReplyTo("reply@sendcloud.org");
		// 设置标题
		body.setSubject("来自 SendCloud SDK 的邮件");
		// 创建文件附件
		body.addAttachments(new File("D:/1.png"));

		// 使用邮件模板
		TemplateContent content = new TemplateContent();
		content.setTemplateInvokeName("sendcloud_account_bind");

		SendCloudMail mail = new SendCloudMail();
		mail.setTo(receiver);
		mail.setBody(body);
		mail.setContent(content);

		SendCloud sc = SendCloudBuilder.build();
		ResponseData res = sc.sendMail(mail);
		System.out.println(res);
	}

	public static void main(String[] args) throws Throwable {
		//send_common();
		// send_common_advanced();
		// send_template();
		// send_with_addresslist();
	}
}
