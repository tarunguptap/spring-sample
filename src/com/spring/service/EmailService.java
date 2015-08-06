package com.spring.service;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bean.EmailAttachmentBean;


@Transactional(readOnly = true)
public interface EmailService {

    public void sendJobFailureMessage(String jobName, Exception e);

    @Transactional(noRollbackFor = MailException.class)
    public void sendJobSuccessMessage(String jobName, String message);

    @Transactional(noRollbackFor = MailException.class)
    public void sendBinClearReport(List<String> messages, final String emailAddress);

    @Transactional(noRollbackFor = MailException.class)
    public void sendASNItemFileErrorReport(List<String> messages, final String emailAddress, final String subject);

    @Transactional(noRollbackFor = Exception.class)
    public void sendEmail(String toEmail, String fromEmail, String subject, String message,
            EmailAttachmentBean... attachments);

}
