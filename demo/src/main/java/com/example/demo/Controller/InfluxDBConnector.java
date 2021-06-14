package com.example.demo.Controller;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

public class InfluxDBConnector { //연결용 클래스
	
	private static final String DB_URL = "http://172.16.200.88:8086";
	private static final String DB_ID = "test";
	private static final String DB_PWD = "test";
	
	private static InfluxDBConnector inf;
	private InfluxDB db = null;
	
	private InfluxDBConnector() {}
	
	public static InfluxDBConnector getInstance() {
		synchronized(InfluxDBConnector.class) {
			if(inf == null) {
				inf = new InfluxDBConnector();
				inf.tryToConnecting();
			}
		}
		return inf;
	}
	
	private void tryToConnecting() {
		db = InfluxDBFactory.connect(DB_URL,DB_ID,DB_PWD);
	}
	
	public InfluxDB getDB() {
		try {
			if(db == null || db.ping() == null) {
				synchronized(inf) {
					db = InfluxDBFactory.connect(DB_URL,DB_ID,DB_PWD);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return db;
	}

}
