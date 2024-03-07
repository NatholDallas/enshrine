package nathol.utils.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String email;

    private String username;

    private String password;

    private String avator;

}
