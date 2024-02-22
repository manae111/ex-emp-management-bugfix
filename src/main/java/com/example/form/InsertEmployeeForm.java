package com.example.form;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class InsertEmployeeForm {
    /** 従業員名 */
    @NotBlank(message = "名前を入力してください")
	private String name;
	/** 画像 */
    @NotBlank(message = "画像を添付してください")
	private String image;
	/** 性別 */
    @NotBlank(message = "性別を入力してください")
	private String gender;
	/** 入社日 */
    @NotBlank(message = "入社日を入力してください")
	private Date hireDate;
	/** メールアドレス */
    @NotBlank(message = "メールアドレスを入力してください")
	private String mailAddress;
	/** 郵便番号 */
    @NotBlank(message = "郵便番号を入力してください")
	private String zipCode;
	/** 住所 */
    @NotBlank(message = "住所を入力してください")
	private String address;
	/** 電話番号 */
    @NotBlank(message = "電話番号を入力してください")
	private String telephone;
	/** 給料 */
    @NotBlank(message = "給料を入力してください")
	private Integer salary;
	/** 特性 */
    @NotBlank(message = "特性を入力してください")
	private String characteristics;
	/** 扶養人数 */
    @NotBlank(message = "扶養人数を入力してください")
	private Integer dependentsCount;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public String getCharacteristics() {
        return characteristics;
    }
    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
    public Integer getDependentsCount() {
        return dependentsCount;
    }
    public void setDependentsCount(Integer dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
    @Override
    public String toString() {
        return "InsertEmployeeForm [name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate=" + hireDate
                + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address + ", telephone="
                + telephone + ", salary=" + salary + ", characteristics=" + characteristics + ", dependentsCount="
                + dependentsCount + "]";
    }

    
}
