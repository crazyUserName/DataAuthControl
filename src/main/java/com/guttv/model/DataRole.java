package com.guttv.model;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table b_data_role
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class DataRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_data_role.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column b_data_role.dataRoleName
     *
     * @mbg.generated
     */
    private String dataRoleName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_data_role.id
     *
     * @return the value of b_data_role.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_data_role.id
     *
     * @param id the value for b_data_role.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column b_data_role.dataRoleName
     *
     * @return the value of b_data_role.dataRoleName
     *
     * @mbg.generated
     */
    public String getDataRoleName() {
        return dataRoleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column b_data_role.dataRoleName
     *
     * @param dataRoleName the value for b_data_role.dataRoleName
     *
     * @mbg.generated
     */
    public void setDataRoleName(String dataRoleName) {
        this.dataRoleName = dataRoleName == null ? null : dataRoleName.trim();
    }
}