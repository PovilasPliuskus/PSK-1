package com.example.bookstore.mybatis.model;

public class Bookorder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.BOOKORDER.FKBOOKID
     *
     * @mbg.generated Sat Apr 12 14:03:55 EEST 2025
     */
    private Integer fkbookid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.BOOKORDER.FKORDERID
     *
     * @mbg.generated Sat Apr 12 14:03:55 EEST 2025
     */
    private Integer fkorderid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.BOOKORDER.FKBOOKID
     *
     * @return the value of PUBLIC.BOOKORDER.FKBOOKID
     *
     * @mbg.generated Sat Apr 12 14:03:55 EEST 2025
     */
    public Integer getFkbookid() {
        return fkbookid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.BOOKORDER.FKBOOKID
     *
     * @param fkbookid the value for PUBLIC.BOOKORDER.FKBOOKID
     *
     * @mbg.generated Sat Apr 12 14:03:55 EEST 2025
     */
    public void setFkbookid(Integer fkbookid) {
        this.fkbookid = fkbookid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.BOOKORDER.FKORDERID
     *
     * @return the value of PUBLIC.BOOKORDER.FKORDERID
     *
     * @mbg.generated Sat Apr 12 14:03:55 EEST 2025
     */
    public Integer getFkorderid() {
        return fkorderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.BOOKORDER.FKORDERID
     *
     * @param fkorderid the value for PUBLIC.BOOKORDER.FKORDERID
     *
     * @mbg.generated Sat Apr 12 14:03:55 EEST 2025
     */
    public void setFkorderid(Integer fkorderid) {
        this.fkorderid = fkorderid;
    }
}