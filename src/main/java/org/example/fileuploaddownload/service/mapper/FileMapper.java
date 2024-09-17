package org.example.fileuploaddownload.service.mapper;

import org.example.fileuploaddownload.dto.FileDto;
import org.example.fileuploaddownload.model.FileEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileMapper {
    private static final Map<Character, String> krillToLatinMap = new HashMap<>();

    static {
        krillToLatinMap.put('А', "A");
        krillToLatinMap.put('Б', "B");
        krillToLatinMap.put('В', "V");
        krillToLatinMap.put('Г', "G");
        krillToLatinMap.put('Д', "D");
        krillToLatinMap.put('Е', "Ye");
        krillToLatinMap.put('Ё', "Yo");
        krillToLatinMap.put('Ў',"O`");
        krillToLatinMap.put('ў',"o`");
//        ў
        krillToLatinMap.put('Ж', "J");
        krillToLatinMap.put('З', "Z");
        krillToLatinMap.put('И', "I");
        krillToLatinMap.put('Й', "Y");
        krillToLatinMap.put('К', "K");
        krillToLatinMap.put('Л', "L");
        krillToLatinMap.put('М', "M");
        krillToLatinMap.put('Н', "N");
        krillToLatinMap.put('О', "O");
        krillToLatinMap.put('П', "P");
        krillToLatinMap.put('Р', "R");
        krillToLatinMap.put('С', "S");
        krillToLatinMap.put('Т', "T");
        krillToLatinMap.put('У', "U");
        krillToLatinMap.put('Ф', "F");
        krillToLatinMap.put('Х', "X");
        krillToLatinMap.put('Ц', "Ts");
        krillToLatinMap.put('Ч', "Ch");
        krillToLatinMap.put('Ш', "Sh");
        krillToLatinMap.put('Щ', "Sh");
        krillToLatinMap.put('Ь', "'");
        krillToLatinMap.put('Ы', "Y");
        krillToLatinMap.put('Э', "E");
        krillToLatinMap.put('Ю', "Yu");
        krillToLatinMap.put('Я', "Ya");
        krillToLatinMap.put('а', "a");
        krillToLatinMap.put('б', "b");
        krillToLatinMap.put('в', "v");
        krillToLatinMap.put('г', "g");
        krillToLatinMap.put('д', "d");
        krillToLatinMap.put('е', "ye");
        krillToLatinMap.put('ё', "yo");
        krillToLatinMap.put('ж', "j");
        krillToLatinMap.put('з', "z");
        krillToLatinMap.put('и', "i");
        krillToLatinMap.put('й', "y");
        krillToLatinMap.put('к', "k");
        krillToLatinMap.put('л', "l");
        krillToLatinMap.put('м', "m");
        krillToLatinMap.put('н', "n");
        krillToLatinMap.put('о', "o");
        krillToLatinMap.put('п', "p");
        krillToLatinMap.put('р', "r");
        krillToLatinMap.put('с', "s");
        krillToLatinMap.put('т', "t");
        krillToLatinMap.put('у', "u");
        krillToLatinMap.put('ф', "f");
        krillToLatinMap.put('х', "x");
        krillToLatinMap.put('ц', "ts");
        krillToLatinMap.put('ч', "ch");
        krillToLatinMap.put('ш', "sh");
        krillToLatinMap.put('щ', "sh");
        krillToLatinMap.put('ь', "'");
        krillToLatinMap.put('ы', "y");
        krillToLatinMap.put('э', "e");
        krillToLatinMap.put('ю', "yu");
        krillToLatinMap.put('я', "ya");
    }

    public  String convertKrillToLatin(String krillText) {
        StringBuilder latinText = new StringBuilder();
        for (char c : krillText.toCharArray()) {
            latinText.append(krillToLatinMap.getOrDefault(c, String.valueOf(c)));
        }
        return latinText.toString();
    }
    public FileEntity toEntity(FileDto dto){
        return FileEntity.builder()
                .name(dto.getName())
                .type(dto.getType())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .build();
    }
    public FileDto toDto(FileEntity entity){
        return FileDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
    public List<FileDto>toDtos(List<FileEntity> entities){
        List<FileDto> dtos = new ArrayList<>();
        for(FileEntity entity : entities){
            dtos.add(toDto(entity));
        }
        return dtos;
    }



}
