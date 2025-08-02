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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_kadai_form.form.ContactForm;

@Controller
public class ContactFormController {
	//お問い合わせフォームの初期表示 (GETリクエスト)
	@GetMapping("/form")//URL
	public String Form(Model model) {
		if (!model.containsAttribute("contactForm")) {
			model.addAttribute("contactForm", new ContactForm());
		}
		return "contactFormView";
	}
	

	//お問い合わせフォームの送信処理 (POSTリクエスト)
	@PostMapping("/confirm")// バリデーションエラーがある場合は入力フォームへ
	public String confirm(@ModelAttribute @Valid ContactForm contactForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", bindingResult);
	        redirectAttributes.addFlashAttribute("contactForm", contactForm);
			return "redirect:/form";
			
	}
		// バリデーションOK時は確認画面へ
		model.addAttribute("contactForm", contactForm);
		return "confirmView";
	}
}
