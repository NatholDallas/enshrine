package nathol.utils.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Star {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long user;

    private String name;

    private String image;

    private String description;

}
