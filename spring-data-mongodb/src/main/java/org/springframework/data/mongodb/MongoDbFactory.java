/*
 * Copyright 2011-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb;

import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;

import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;

/**
 * Interface for factories creating {@link DB} instances.
 *
 * @author Mark Pollack
 * @author Thomas Darimont
 * @author Christoph Strobl
 */
public interface MongoDbFactory extends CodecRegistryProvider {

	/**
	 * Creates a default {@link DB} instance.
	 *
	 * @return
	 * @throws DataAccessException
	 */
	MongoDatabase getDb() throws DataAccessException;

	/**
	 * Creates a {@link DB} instance to access the database with the given name.
	 *
	 * @param dbName must not be {@literal null} or empty.
	 * @return
	 * @throws DataAccessException
	 */
	MongoDatabase getDb(String dbName) throws DataAccessException;

	/**
	 * Exposes a shared {@link MongoExceptionTranslator}.
	 *
	 * @return will never be {@literal null}.
	 */
	PersistenceExceptionTranslator getExceptionTranslator();

	DB getLegacyDb();

	/**
	 * Get the underlying {@link CodecRegistry} used by the MongoDB Java driver.
	 *
	 * @return never {@literal null}.
	 */
	@Override
	default CodecRegistry getCodecRegistry() {
		return getDb().getCodecRegistry();
	}
}
