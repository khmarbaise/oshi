/**
 * Oshi (https://github.com/dblock/oshi)
 *
 * Copyright (c) 2010 - 2016 The Oshi Project Team
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Maintainers:
 * dblock[at]dblock[dot]org
 * widdis[at]gmail[dot]com
 * enrico.bianchi[at]gmail[dot]com
 *
 * Contributors:
 * https://github.com/dblock/oshi/graphs/contributors
 */
package oshi.json.hardware.impl;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import oshi.json.hardware.CentralProcessor;
import oshi.json.json.NullAwareJsonObjectBuilder;

public class CentralProcessorImpl implements CentralProcessor {

    private static final long serialVersionUID = 1L;

    private JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);

    private oshi.hardware.CentralProcessor processor;

    public CentralProcessorImpl(oshi.hardware.CentralProcessor processor) {
        this.processor = processor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVendor() {
        return this.processor.getVendor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVendor(String vendor) {
        this.processor.setVendor(vendor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.processor.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.processor.setName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getVendorFreq() {
        return this.processor.getVendorFreq();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVendorFreq(long freq) {
        this.processor.setVendorFreq(freq);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        return this.processor.getIdentifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIdentifier(String identifier) {
        this.processor.setIdentifier(identifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCpu64bit() {
        return this.processor.isCpu64bit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCpu64(boolean cpu64) {
        this.processor.setCpu64(cpu64);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStepping() {
        return this.processor.getStepping();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStepping(String stepping) {
        this.processor.setStepping(stepping);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModel() {
        return this.processor.getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(String model) {
        this.processor.setModel(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFamily() {
        return this.processor.getFamily();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFamily(String family) {
        this.processor.setFamily(family);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSystemCpuLoadBetweenTicks() {
        return this.processor.getSystemCpuLoadBetweenTicks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getSystemCpuLoadTicks() {
        return this.processor.getSystemCpuLoadTicks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSystemCpuLoad() {
        return this.processor.getSystemCpuLoad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSystemLoadAverage() {
        return this.processor.getSystemLoadAverage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getSystemLoadAverage(int nelem) {
        return this.processor.getSystemLoadAverage(nelem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getProcessorCpuLoadBetweenTicks() {
        return this.processor.getProcessorCpuLoadBetweenTicks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[][] getProcessorCpuLoadTicks() {
        return this.processor.getProcessorCpuLoadTicks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getSystemUptime() {
        return this.processor.getSystemUptime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSystemSerialNumber() {
        return this.processor.getSystemSerialNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLogicalProcessorCount() {
        return this.processor.getLogicalProcessorCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPhysicalProcessorCount() {
        return this.processor.getPhysicalProcessorCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonObject toJSON() {
        JsonArrayBuilder systemLoadAverageArrayBuilder = jsonFactory.createArrayBuilder();
        for (double avg : getSystemLoadAverage(3)) {
            systemLoadAverageArrayBuilder.add(avg);
        }
        JsonArrayBuilder systemCpuLoadTicksArrayBuilder = jsonFactory.createArrayBuilder();
        for (long ticks : getSystemCpuLoadTicks()) {
            systemCpuLoadTicksArrayBuilder.add(ticks);
        }
        JsonArrayBuilder processorCpuLoadBetweenTicksArrayBuilder = jsonFactory.createArrayBuilder();
        for (double load : getProcessorCpuLoadBetweenTicks()) {
            processorCpuLoadBetweenTicksArrayBuilder.add(load);
        }
        JsonArrayBuilder processorCpuLoadTicksArrayBuilder = jsonFactory.createArrayBuilder();
        for (long[] procTicks : getProcessorCpuLoadTicks()) {
            JsonArrayBuilder processorTicksArrayBuilder = jsonFactory.createArrayBuilder();
            for (long ticks : procTicks) {
                processorTicksArrayBuilder.add(ticks);
            }
            processorCpuLoadTicksArrayBuilder.add(processorTicksArrayBuilder.build());
        }
        return NullAwareJsonObjectBuilder.wrap(jsonFactory.createObjectBuilder()).add("name", getName())
                .add("physicalProcessorCount", getPhysicalProcessorCount())
                .add("logicalProcessorCount", getLogicalProcessorCount())
                .add("systemSerialNumber", getSystemSerialNumber()).add("vendor", getVendor())
                .add("vendorFreq", getVendorFreq()).add("cpu64bit", isCpu64bit()).add("family", getFamily())
                .add("model", getModel()).add("stepping", getStepping())
                .add("systemCpuLoadBetweenTicks", getSystemCpuLoadBetweenTicks())
                .add("systemCpuLoadTicks", systemCpuLoadTicksArrayBuilder.build())
                .add("systemCpuLoad", getSystemCpuLoad()).add("systemLoadAverage", getSystemLoadAverage())
                .add("systemLoadAverages", systemLoadAverageArrayBuilder.build())
                .add("processorCpuLoadBetweenTicks", processorCpuLoadBetweenTicksArrayBuilder.build())
                .add("processorCpuLoadTicks", processorCpuLoadTicksArrayBuilder.build())
                .add("systemUptime", getSystemUptime()).build();
    }

    @Override
    public String toString() {
        return this.processor.toString();
    }
}
