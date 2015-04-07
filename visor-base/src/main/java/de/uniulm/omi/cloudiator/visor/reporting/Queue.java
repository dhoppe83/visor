/*
 * Copyright (c) 2014-2015 University of Ulm
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package de.uniulm.omi.cloudiator.visor.reporting;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.uniulm.omi.cloudiator.visor.execution.ScheduledExecutionService;
import de.uniulm.omi.cloudiator.visor.monitoring.DefaultInterval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * A generic implementation of the reporting interface using a
 * queue as "buffer" for the reporting.
 * All items store in the queue will be later reported to the concrete reporting interface.
 *
 * @param <T> the class of the generic item.
 */
@Singleton public class Queue<T> implements ReportingInterface<T> {

    /**
     * The queue storing the items.
     */
    private final BlockingQueue<T> queueDelegate;
    private static final Logger LOGGER = LogManager.getLogger(Queue.class);

    @Inject public Queue(ScheduledExecutionService executionService,
        QueueWorkerFactoryInterface<T> queueWorkerFactory) {
        this.queueDelegate = new LinkedBlockingQueue<>();
        executionService
            .schedule(queueWorkerFactory.create(this.queueDelegate, new DefaultInterval(20, TimeUnit.SECONDS)));
    }

    @Override public void report(T item) throws ReportingException {
        if (this.queueDelegate.remainingCapacity() == 0) {
            throw new ReportingException("Item could not be reported as queue is full.");
        }
        try {
            this.queueDelegate.put(item);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    @Override public void report(Collection<T> items) throws ReportingException {
        for (T item : items) {
            this.report(item);
        }
    }
}
