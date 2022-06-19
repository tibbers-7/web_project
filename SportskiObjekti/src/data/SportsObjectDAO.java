package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.SportsObject;

public class SportsObjectDAO {
		private HashMap<Integer, SportsObject> sportsObjects= new HashMap<>();
		private String sportsObjectsPath = "";

		public SportsObjectDAO(String contextPath) {
			this.setSportsObjects(new HashMap<Integer, SportsObject>());
			this.setSportsObjectsPath(contextPath);
			loadSportsObjects();
			serialize();
		}
		
		public HashMap<Integer, SportsObject> getSportsObjects() {
			return sportsObjects;
		}
		public Collection<SportsObject> getSportsObjectsCollection() {
			return sportsObjects.values();
		}

		public String getSportsObjectsPath() {
			return sportsObjectsPath;
		}

		public void setSportsObjectsPath(String sportsObjectsPath) {
			this.sportsObjectsPath = sportsObjectsPath;
		}

		public void setSportsObjects(HashMap<Integer, SportsObject> sportsObjects) {
			this.sportsObjects = sportsObjects;
		}
		
	
		public void addSportsObject(SportsObject s) {
			int maxId = 0;
			maxId=getSportsObjectsCollection().size();
			maxId++;
			sportsObjects.put(maxId, s);
			saveSportsObject(s);
		}

		private void saveSportsObject(SportsObject object) {
			File f = new File(sportsObjectsPath + "/sportsObjects.txt");
			FileWriter writer=null;
			try {
				writer = new FileWriter(f);
				writer.write(object.getSportsObjectString()); 
			    writer.flush();
			    writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void serialize() {
			
			try {
				
				File f = new File("c:\\data\\sportsObjects.txt");
				if (f.createNewFile()) {
					BufferedWriter br = new BufferedWriter(new FileWriter(f));
					
					for(SportsObject s:sportsObjects.values()) {
						br.write(s.getSportsObjectString());
					}		br.close();	
					
				} else {
				   // If there was, no harm, no foul
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	private void loadSportsObjects() {
		BufferedReader in = null;
		try {
			File file = new File(sportsObjectsPath + "/sportsObjects.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, name = "", type = "", services = "", isOpen="", location="", avgScore="", openHours="";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals(""))
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					name = st.nextToken().trim();
					type = st.nextToken().trim();
					services = st.nextToken().trim();
					isOpen = st.nextToken().trim();
					location = st.nextToken().trim();
					avgScore = st.nextToken().trim();
					openHours = st.nextToken().trim();
				}
				SportsObject sportsObject=new SportsObject(name,type,services,isOpen,location,avgScore,openHours);
				addSportsObject(sportsObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
}
