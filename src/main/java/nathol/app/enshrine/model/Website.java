package nathol.app.enshrine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Website {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonIgnore
    private Long user;

    private String title;

    private String url;

    private String description;

}
