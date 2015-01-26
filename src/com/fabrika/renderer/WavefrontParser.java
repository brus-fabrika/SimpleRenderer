package com.fabrika.renderer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WavefrontParser {
	private final String OBJ_FILE_PATH;
	
	public List<Vec3f> vertexes = new ArrayList<Vec3f>();
	public List<Face> faces = new ArrayList<Face>();

	public WavefrontParser(String filePath) {
		OBJ_FILE_PATH = filePath;
	}
	
	public void parse() {
		try(BufferedReader reader = new BufferedReader(new FileReader(OBJ_FILE_PATH))){
			String line = reader.readLine();
			while(line != null){
				parseObjLine(line);
				line = reader.readLine();
			}
		} catch (Exception e) { /*NOP*/ }
	}

	private void parseObjLine(String line) {
		
		System.out.println("parseObjLine: " + line);
		
		assert(line != null);
		
		if(line.isEmpty() || line.startsWith("#")) return;
		
		line = line.replaceAll("\\s+", " ");
		
		if(line.startsWith("v ")) {
			String[] vs = line.split(" ");
			System.out.println("parseObjLine: " + Arrays.toString(vs));
			double x = Double.parseDouble(vs[1]);
			double y = Double.parseDouble(vs[2]);
			double z = Double.parseDouble(vs[3]);
			
			vertexes.add(new Vec3f(x, y, z));
		}
		
		if(line.startsWith("f ")) {
			String[] vs = line.split(" ");
			int x = Integer.parseInt(vs[1]);
			int y = Integer.parseInt(vs[2]);
			int z = Integer.parseInt(vs[3]);
			
			faces.add(new Face(x, y, z));
		}
	}
	
}

class Vec3f {
	public double x;
	public double y;
	public double z;
	
	public Vec3f(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "Vec3f [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
}

class Face {
	public int[] v = new int[3];
	
	public Face(int v1, int v2, int v3){
		v[0] = v1;
		v[1] = v2;
		v[2] = v3;
	}

	@Override
	public String toString() {
		return "Face [v=" + Arrays.toString(v) + "]";
	}
	
}