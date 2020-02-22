



import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


import java.beans.Transient;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 所有需要保存到数据库的实体要继承的父类
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/10/19.
 */
@MappedSuperclass
@Getter
@Setter
public class EntityBean implements Serializable {
    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 创建时间
    @JsonIgnore
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Timestamp createTime;

    // 修改时间
    @JsonIgnore
    @Column(updatable = false)
    @org.hibernate.annotations.UpdateTimestamp
    private Timestamp editTime = null;

    @Transient
    private int pageIndex = 0;

    @Transient
    private int pageSize = 10;

    @Transient
    private String sortDirection = "DESC";

    @Transient
    private String sortProperty = "editTime";

    // 搜索关键字
    @Transient
    private String key;


    @JsonIgnore
    public PageRequest getPageRequest() {
        return PageRequest.of(pageIndex, pageSize, new Sort(sortDirection.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, TextUtil.splitToArray(sortProperty)));
    }

    /**
     * 返回修改时间
     *
     * @return
     */
    public String getCreateTimeStr() {
        return ReviewDateFormatUtl.formatFullDate(createTime);
    }

    /**
     * 返回修改时间
     *
     * @return
     */
    public String getEditTimeStr() {
        return ReviewDateFormatUtl.formatFullDate(editTime);
    }


}
