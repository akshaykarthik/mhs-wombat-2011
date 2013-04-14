package edu.mhs.wombat.utils.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Options {
	private static final String COMMENT_HEADER = ">>";
	private static final String SEPARATOR_REGEX = "\\|\\|";

	private static HashMap<String, Option> opts = new HashMap<String, Option>();

	public static void load() {
		File file = new File("game.opts");
		System.out.println(file.toString());

		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while (in.ready()) {
				String s = in.readLine();
				if (s.indexOf(COMMENT_HEADER) < 0) {
					String[] vars = s.split(SEPARATOR_REGEX);
					System.out.println(Arrays.toString(vars));
					opts.put(vars[1].trim(),
							new Option(vars[2].trim(), vars[0].trim()));

				}
			}

			in.close();

		} catch (FileNotFoundException e) {
			setToDefaults();
			Options.save(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setToDefaults() {
		opts.put("name", new Option("Default User", "text"));
		opts.put("volume", new Option("100", "number"));
		opts.put("difficulty", new Option("easy", "difficulty"));
	}

	public static String get(String option) {
		return opts.get(option).value;
	}

	public static void set(String option, String value) {
		opts.get(option).value = value;
	}

	public static String getType(String option) {
		return opts.get(option).type;
	}

	public static void setType(String option, String type) {
		opts.get(option).type = type;
	}

	public static HashMap<String, Option> getOptions() {
		return opts;
	}

	public void save() {
		save(false);
	}

	public static void save(boolean def) {
		try {
			File file = new File("game.opts");

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			Iterator<Entry<String, Option>> it = opts.entrySet().iterator();
			if (def) {
				bw.write(COMMENT_HEADER
						+ "Default Generated Configuration File \n");
			}
			while (it.hasNext()) {
				Map.Entry<String, Option> pairs = it.next();
				bw.write(// write entries to file
				String.format("%s || %s || %s \n", // format of save file
						pairs.getValue().type, // type of resource
						pairs.getKey(), // resource name
						pairs.getValue().value // value of resource
				));

				it.remove();
			}
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
