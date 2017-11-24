package com.skipthedishes.api;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseTest {

	@Before
	public void init() {
        MockitoAnnotations.initMocks(this);
    }
}
