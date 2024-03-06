package com.app.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.constants.AppConstants;
import com.app.model.HeaderKeys;
import com.app.model.User;
import com.app.response.Response;
import com.app.util.NullUtil;

@Component
public class HeaderHelper {
	
	
	@Value("${PRODUCTION_BASE_URL}")
	private String productionBaseUrl;
	@Value("${SANDBOX_BASE_URL}")
	private String sandboxBaseUrl;
	
	public Map<String, String> removeHeaderValue(final Map<String, String> header, String... keys) {
		List<String> headerItems = new ArrayList<>();
		String[] array = { "host", "connection", "accept-encoding", "user-agent", "referer", "origin", "dnt",
				"accept-language", "accept", "content-length", "cookie", "x-forwarded-for", "x-forwarded-proto",
				"x-forwarded-port", "x-amzn-trace-id", "sec-ch-ua", "sec-ch-ua-mobile" };
		// Add all elements in array to ArrayList.
		Collections.addAll(headerItems, array);
		headerItems.forEach(headerItem -> {
			if (header.containsKey(headerItem)) {
				header.remove(headerItem);
			}
		});
		for (String key : keys) {
			if (header.containsKey(key))
				header.remove(key);
		}
		return header;
	}

	public Response isEnableKeys(Map<String, String> header, User user) {
		Response response = new Response();

		if (NullUtil.isEmpty(user)) {
			response.setStatuscd(AppConstants.ZERO);
			response.setStatus(AppConstants.USER_NOT_FOUND);
		}
		return isEnableKeys(header, user, response);
	}

	public Response isEnableKeys(Map<String, String> header, User user, Response response) {
		response.setStatuscd(AppConstants.ONE);

		if (NullUtil.isNotEmpty(user)) {
			if (NullUtil.isNotEmpty(user.isDisable()) && user.isDisable()) {
				return new Response(AppConstants.ZERO, AppConstants.DISABLE_USER);
			}

			String stage = getStage(header, user);
			if ((NullUtil.isEmpty(stage))) {
				return new Response(AppConstants.ZERO, AppConstants.SUBSCRIPTION_EXPIRED);
			}
			List<HeaderKeys> lUserKeys = user.getKeys();
			if (NullUtil.isEmpty(lUserKeys)) {
				return new Response(AppConstants.ZERO, AppConstants.KEYS_DISABLED);
			}
		}
		return response;
	}

	public String getUrl(Map<String, String> header, User user) {
		String stage = getStage(header, user);
		if (AppConstants.PRODUCTION.equalsIgnoreCase(stage.trim())) {
			return productionBaseUrl;
		} else {
			return sandboxBaseUrl;
		}
	}

	public String getStage(Map<String, String> header, User user) {
		String stage = null;
		if (user != null && header.containsKey(AppConstants.USER_CLIENTID)
				&& header.containsKey(AppConstants.USER_CLIENTSECRET)) {
			List<HeaderKeys> headerKeys = user.getKeys();
			if (NullUtil.isNotEmpty(headerKeys)) {
				for (HeaderKeys keys : headerKeys) {
					if ((keys.getClientid().trim().equals(header.get(AppConstants.USER_CLIENTID)))
							&& (keys.getClientsecret().trim().equals(header.get(AppConstants.USER_CLIENTSECRET)))) {
						if (NullUtil.isNotEmpty(keys.getStage())) {
							if (AppConstants.PRODUCTION.equals(keys.getStage().trim())) {
								stage = AppConstants.PRODUCTION;
							} else if (AppConstants.PREPRODUCTION.equals(keys.getStage().trim())) {
								stage = AppConstants.PREPRODUCTION;
							}
						}
					}
				}
			}
		}
		return stage;
	}

}
