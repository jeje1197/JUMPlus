package com.sgb.connection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class BetterConnectionManagerTest {

	@Test
	void testConnectionToDatabase() {
		Connection connection = BetterConnectionManager.ConnManagerWithProperties.getConnection();
		assertNotNull(connection);
	}

}
