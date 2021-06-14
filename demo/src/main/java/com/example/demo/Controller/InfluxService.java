package com.example.demo.Controller;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.stereotype.Service;

@Service
public class InfluxService {
	
	private static final InfluxDBConnector conn = InfluxDBConnector.getInstance();
	protected static final InfluxDB infDB = conn.getDB();
	private static final String DBName = "testdb";
	
	public int getList() {
		String str = "select "
				+ "bedNumber"
				+ ",apnea"
				+ ",distance"
				+ ",fallDetection"
				+ ",fallprevention"
				+ ",movement"
				+ ",presence"
				+ ",time "
				+ "from BedData";
		Query query = new Query(str,DBName);
		QueryResult ask = infDB.query(query);
		System.out.println(ask);
		var cols = ask.getResults().get(0).getSeries().get(0).getColumns();
		var values = ask.getResults().get(0).getSeries().get(0).getValues();
		System.out.println(cols);
		System.out.println(values);
		
		int result = 0;
		
		for(int i=0;i<cols.size();i++) {
			if(cols.get(i).equals("bedNumber")) {
				result = Integer.parseInt(values.get(i).toString());
			}
		}
		
		return result;
		
	}

}
