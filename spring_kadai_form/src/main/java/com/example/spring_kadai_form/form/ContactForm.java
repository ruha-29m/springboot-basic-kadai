package com.example.spring_kadai_form.form;
/*フォームクラスを定義し、お問い合わせフォームの各入力項目を管理。
 * バリデーション条件（後述）も設定する。*/

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

	@Data//フォームクラスの定義,フォームクラスのデータ読み書きに必要な、ゲッターとセッターを自動追加できます
	public class ContactForm {
	    // 名前
		@NotBlank(message = "名前を入力してください。")
	    private String name;

	    // メールアドレス
		@NotBlank(message = "メールアドレスを入力してください。")
	    @Email(message = "有効なメールアドレスを入力してください。")
	    private String email;

	    //お問い合わせ内容
		 @NotBlank(message = "お問い合わせ内容を入力してください。")
	    private String message;
	}

 