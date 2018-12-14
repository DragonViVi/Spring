package com.lyl.batch.model;

/**
 * Date:2018/12/13 0013 下午 4:26
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 下午 4:26
 */
public class Template {

    private String name;
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Template() {
    }

    public Template(String name, int status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Template{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
