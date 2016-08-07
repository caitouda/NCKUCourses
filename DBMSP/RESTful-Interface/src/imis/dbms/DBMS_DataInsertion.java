package imis.dbms;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.lang.String;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Path("/IMISDBMS")
public class DBMS_DataInsertion {
	private static Queue<String> list = new ConcurrentLinkedQueue<String>();
	private static Mongo mongo = new Mongo("localhost", 27017);
	private static DB db = mongo.getDB("IMISDBMS");
	private static DBCollection collection = db.getCollection("DBMSTest");
	private static DBCollection collection2 = db.getCollection("DBMSTest2");
	private static long num = 0;
	private static long rightnum = 0;
	private static double value = 0;
	private static double preavg = 0;
	private static double nowavg = 0;
	private static long flag = 0;

	static {
		try {
			new Thread(new Runner()).start();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("/DataInsertion/{inputParameter}")
	@Produces(MediaType.APPLICATION_JSON)
	public String setData(@PathParam("inputParameter") String InsertData) throws UnknownHostException {
		list.add(InsertData);
		if (num < 50000) {
			value = Double.valueOf(InsertData) - preavg;
			nowavg = preavg + (value) / (num + 1);
			preavg = nowavg;
		}
		num++;
		return null;
	}

	@GET
	@Path("/SelectCurrentData")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCurrentData() throws UnknownHostException {
		long count = 0;
		try {
			count = collection.count() + collection2.count();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return "jsonpCallback(" + count + ")";
	}

	static class Runner extends Thread {
		@Override
		public void run() {
			String str;
			while (true) {
				if (num >= 50000) {
					str = list.poll();
					if (str != null) {
						BasicDBObject document = new BasicDBObject();
						document.put("data", str);
						value = Double.valueOf(str);
						if (value >= 0.9 * nowavg && value <= 1.1 * nowavg) {
							if (flag == 0) {
								flag = 1;
								preavg = 0;
							}
							value = Double.valueOf(str) - preavg;
							nowavg = preavg + (value) / (rightnum + 1);
							preavg = nowavg;
							rightnum++;
							collection.insert(document);
						} else {
							collection2.insert(document);
						}
					}
				}
				/*
				 * else{ try { Thread.sleep(10000); } catch
				 * (InterruptedException e){ //nothing } //out.flush(); }
				 */
				else
					System.out.println("nohello");
			}
		}
	}
}
