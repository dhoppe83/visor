/*
 *
 *  * Copyright (c) 2014 University of Ulm
 *  *
 *  * See the NOTICE file distributed with this work for additional information
 *  * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 *  * "License"); you may not use this file except in compliance
 *  * with the License.  You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied.  See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *
 */

package de.uniulm.omi.monitoring.server.impl;

import com.google.inject.Inject;
import de.uniulm.omi.monitoring.execution.api.ExecutionServiceInterface;
import de.uniulm.omi.monitoring.server.api.ServerListenerFactoryInterface;

import java.net.ServerSocket;

/**
 * Created by daniel on 16.12.14.
 */
public class ServerListenerFactory implements ServerListenerFactoryInterface {

    private final SocketWorkerFactory socketWorkerFactory;
    private final ExecutionServiceInterface executionService;

    @Inject
    public ServerListenerFactory(ExecutionServiceInterface executionService, SocketWorkerFactory socketWorkerFactory) {
        this.executionService = executionService;
        this.socketWorkerFactory = socketWorkerFactory;
    }

    @Override
    public ServerListener create(ServerSocket serverSocket) {
        return new ServerListener(serverSocket, this.executionService, this.socketWorkerFactory);
    }
}