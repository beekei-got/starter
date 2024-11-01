package com.starter.app.presentation;

import com.beekei.library.mockTest.MockMvcControllerTest;
import com.starter.app.config.payload.ApiResponse;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;

@ActiveProfiles("local")
public class ControllerTest extends MockMvcControllerTest {

	protected ControllerTest(Class<?> controllerClass) {
		super(controllerClass);
	}

	@Override
	protected HashMap<String, Object> getResponseBodyTemplate() {
		return new HashMap<>() {{
			put("resultCode", ApiResponse.SUCCESS_CODE);
			put("resultMessage", ApiResponse.SUCCESS_MESSAGE);
			put("resultData", "{responseBody}");
		}};
	}

}
