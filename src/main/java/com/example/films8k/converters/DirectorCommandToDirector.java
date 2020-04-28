package com.example.films8k.converters;

import com.example.films8k.commands.DirectorCommand;
import com.example.films8k.model.Director;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DirectorCommandToDirector implements Converter<DirectorCommand, Director> {

    @Synchronized
    @Nullable
    @Override
    public Director convert(DirectorCommand source) {
        if (source == null) {
            return null;
        }

        final Director director = new Director();
        director.setFirstName(source.getFirstName());
        director.setLastName(source.getLastName());
        director.setAge(source.getAge());

        return director;
    }
}
