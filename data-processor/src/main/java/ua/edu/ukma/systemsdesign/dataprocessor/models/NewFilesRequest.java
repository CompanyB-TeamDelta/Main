package ua.edu.ukma.systemsdesign.dataprocessor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewFilesRequest {
    private Date fetched_at;
    private List<String> files;
}
