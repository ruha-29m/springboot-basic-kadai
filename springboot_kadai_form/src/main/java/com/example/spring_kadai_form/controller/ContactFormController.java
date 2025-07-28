package com.example.spring_kadai_form.controller;
/*フォーム・確認画面への各リクエストに応じて、適切なビューを表示。
バリデーション結果に応じて、表示内容を切り替える必要がある。
バリデーションOK：確認画面を表示
バリデーションNG：元のフォームへリダイレクト*/

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring_kadai_form.form.ContactForm;

@Controller
public class ContactFormController {

	@GetMapping("/contact")
	public String ContactForm(Model model) {
		if (!model.containsAttribute("contactForm")) {
			model.addAttribute("contactForm", new ContactForm());
		}
		return "contactFormView";
	}

	@PostMapping("/contact")// バリデーションエラーがある場合は入力フォームへ
	public String confirm(@Valid ContactForm contactForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
		return "contactFormView";
	}
		// バリデーションOK時は確認画面へ
		model.addAttribute("contactForm", contactForm);
		return "confirm";
	}
}

/*
 * 
 * 
名前
（name）	input	type="text"	未入力であればNG
メールアドレス
（email）	input	type="text"	未入力であればNG／メールアドレス形式でなければNG
お問い合わせ内容
（message）	textarea	cols="30"（横幅30文字）
rows="10"（縦幅10文字）	未入力であればNG
*/
