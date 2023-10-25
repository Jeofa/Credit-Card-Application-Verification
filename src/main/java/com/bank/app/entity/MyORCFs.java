package com.bank.app.entity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessMode;
import java.nio.file.DirectoryStream;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Map;
import java.util.Set;

import org.graalvm.polyglot.io.FileSystem;

public class MyORCFs implements FileSystem {
	
	  private String moduleName = "performVerification";
	  private Path path = Paths.get("/home/jeff/Documents/SpringBootData/CCAV-V3/src/main/resources/static/js/analyser/node_modules/idanalyzer/pers.mjs");

	  private FileSystem defaultFs = FileSystem.newDefaultFileSystem();

	  @Override
	  public Path parsePath(URI uri) {
	    if(moduleName.equals(uri)) {
	      return path;
	    }
	    return defaultFs.parsePath(uri);
	  }

	  @Override
	  public Path parsePath(String path) {
	    if(moduleName.equals(path)) {
	      return this.path;
	    }
	    return defaultFs.parsePath(path);
	  }

	  @Override
	  public void checkAccess(Path path, Set<? extends AccessMode> modes, LinkOption... linkOptions) throws IOException {
	    defaultFs.checkAccess(path, modes, linkOptions);
	  }

	  @Override
	  public void createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {
	    defaultFs.createDirectory(dir, attrs);
	  }

	  @Override
	  public void delete(Path path) throws IOException {
	    defaultFs.delete(path);
	  }

	  @Override
	  public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException {
	    if(this.path.equals(path)) {
	      return new RandomAccessFile(path.toFile(), "r").getChannel();
	    }
	    return defaultFs.newByteChannel(path, options, attrs);
	  }

	  @Override
	  public DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException {
	    return defaultFs.newDirectoryStream(dir, filter);
	  }

	  @Override
	  public Path toAbsolutePath(Path path) {
	    return defaultFs.toAbsolutePath(path);
	  }

	  @Override
	  public Path toRealPath(Path path, LinkOption... linkOptions) throws IOException {
	    return defaultFs.toRealPath(path, linkOptions);
	  }

	  @Override
	  public Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
	    return defaultFs.readAttributes(path, attributes, options);
	  }

}
