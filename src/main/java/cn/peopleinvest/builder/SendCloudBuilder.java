package cn.peopleinvest.builder;

import cn.peopleinvest.config.MailConfig;
import cn.peopleinvest.core.SendCloud;

public class SendCloudBuilder {

	public static SendCloud build() {
		SendCloud sc = new SendCloud();
		sc.setServer(MailConfig.server);
		sc.setMailAPI(MailConfig.send_api);
		sc.setTemplateAPI(MailConfig.send_template_api);
		sc.setSmsAPI(MailConfig.send_sms_api);
		sc.setVoiceAPI(MailConfig.send_voice_api);
		return sc;
	}
}