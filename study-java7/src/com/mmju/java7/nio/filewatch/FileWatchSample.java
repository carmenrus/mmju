package com.mmju.java7.nio.filewatch;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatchSample {

	public FileWatchSample() {

		try {
			FileSystem fs = FileSystems.getDefault();
			
			Path path = fs.getPath("alpha");
			WatchService watcher = fs.newWatchService();

			path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			
			while(true) {
				WatchKey key = null;
				try {
					key = watcher.take();
				} catch (InterruptedException e) {
					break;
				}
				
				for(WatchEvent<?> event : key.pollEvents()) {
					if(event.kind() == StandardWatchEventKinds.OVERFLOW) {
						continue;
					} else {
						System.out.println(event.kind() + " : " + event.context());
					}
				}
				
				if(!key.reset()) {
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new FileWatchSample();
	}

}
