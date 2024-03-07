package nathol.utils.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Website {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long user;

    private String title;

    private String url;

    private String description;

}
