package com.drsg.demo.v1.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page<T> implements IPage<T> {
    protected List<T> records;
    protected long total;
    @JsonIgnore
    protected long size;
    @JsonIgnore
    protected long current;
    @JsonIgnore
    protected List<OrderItem> orders;

    public Page() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.orders = new ArrayList<>();
    }

    public Page(long current, long size) {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.orders = new ArrayList<>();
        if (current > 1L)
            this.current = current;
        if (size > 0L)
            this.size = size;
    }

    @Override
    public List<OrderItem> orders() {
        return this.getOrders();
    }

    public List<OrderItem> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders.addAll(orders);
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @JsonIgnore
    @Override
    public boolean isSearchCount() {
        return true;
    }

    @JsonIgnore
    @Override
    public long getPages() {
        return 1;
    }

    @JsonIgnore
    @Override
    public boolean isHitCount() {
        return false;
    }
}
