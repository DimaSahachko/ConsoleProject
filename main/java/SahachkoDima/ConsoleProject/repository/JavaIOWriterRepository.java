package SahachkoDima.ConsoleProject.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.google.gson.Gson;

import SahachkoDima.ConsoleProject.model.Writer;

public class JavaIOWriterRepository implements WriterRepository {

	Path writersStorage = Paths.get("src\\main\\resources\\files\\writers.json");
	Gson gson = new Gson();
	
	@Override
	public List<Writer> getAll() {
		List<Writer> allWriters = new ArrayList<>();
		if(Files.notExists(writersStorage)) {
			try {
				Files.createFile(writersStorage);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		try(Stream<String> stream = Files.lines(writersStorage)) {
			stream.map(line -> gson.fromJson(line, Writer.class)).forEach(writer -> allWriters.add(writer));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return allWriters;
	}

	@Override
	public Writer getById(Long id) {
		Writer writer = null;
		try(Stream<String> stream = Files.lines(writersStorage)) {
			Optional<Writer> optionalWriter = stream.map(line -> gson.fromJson(line, Writer.class))
					.filter(currentWriter -> currentWriter.getId().equals(id)).findFirst();
			if(optionalWriter.isPresent()) {
				writer = optionalWriter.get();
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return writer;
	}

	@Override
	public Writer save(Writer writer) {
		long nextEmptyId = 0;
		List<Writer> allWriters = new ArrayList<>();
		try{
			if (Files.notExists(writersStorage)) {
				Files.createFile(writersStorage);
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		try(Stream<String> stream = Files.lines(writersStorage)) {
			stream.map(line -> gson.fromJson(line, Writer.class)).forEach(writerObj -> allWriters.add(writerObj));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		if(allWriters.size() == 0) {
			nextEmptyId = 1;
		} else {
			long counter = 0;
			for(Writer currentWriter : allWriters) {
				if(currentWriter.getId() > counter) {
					counter = currentWriter.getId();
					nextEmptyId = counter + 1;
				}
			}
		}
		writer.setId(nextEmptyId);
		String writerJsonRepresentation = gson.toJson(writer);
		try{
			Files.write(writersStorage, (writerJsonRepresentation + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return writer;
	}

	@Override
	public Writer update(Writer writer) {
		List<Writer> allWritersBeforeUpdating = getAll();
		List<Writer> allWritersAfterUpdating = new ArrayList<>();
		for(Writer currentWriter : allWritersBeforeUpdating) {
			if(currentWriter.getId().equals(writer.getId())) {
				allWritersAfterUpdating.add(writer);
				continue;
			}
			allWritersAfterUpdating.add(currentWriter);
		}
		if(allWritersBeforeUpdating.equals(allWritersAfterUpdating)) {
			return null;
		} else {
			try {
				Files.delete(writersStorage);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
			allWritersAfterUpdating.stream().map(currentWriter -> gson.toJson(currentWriter))
			.forEach(jsonWriter -> {
					try {
						Files.write(writersStorage, (jsonWriter + System.lineSeparator()).getBytes(), 
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
					} catch (IOException exc) {
						exc.printStackTrace();
					}
				});
		}
		return writer;
	}

	@Override
	public void deleteById(Long id) {
		List<Writer> allWritersBeforeUpdating = getAll();
		List<Writer> allWritersAfterUpdating = new ArrayList<>();
		for(Writer currentWriter : allWritersBeforeUpdating) {
			if(currentWriter.getId().equals(id)) {
				continue;
			}
			allWritersAfterUpdating.add(currentWriter);
		}
		if(allWritersBeforeUpdating.equals(allWritersAfterUpdating)) {
			System.out.println("There is no region with wuch id");
		} else {
			try {
				Files.delete(writersStorage);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
			allWritersAfterUpdating.stream().map(currentWriter -> gson.toJson(currentWriter))
			.forEach(jsonWriter -> {
					try {
						Files.write(writersStorage, (jsonWriter + System.lineSeparator()).getBytes(), 
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
					} catch (IOException exc) {
						exc.printStackTrace();
					}
				});
		}
	}
	
}
