package com.spring.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.spring.bean.EmailAttachmentBean;
import com.spring.service.EmailService;

@Service
public class EmailServiceImpl extends ApplicationObjectSupport implements EmailService {

    Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private JavaMailSender mailSender;


    public void init() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost("SMTP_HOST");
        mailSenderImpl.setUsername("SMTP_USERNAME");
        mailSenderImpl.setPassword("SMTP_PASSWORD");
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "SMTP_AUTH");
        mailSenderImpl.setJavaMailProperties(javaMailProperties);
        mailSender = mailSenderImpl;
    }

    /**
     * @param mailSender
     *            the mailSender to set
     */
    public final void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendJobFailureMessage(final String jobName, final Exception e) {
        final String toEmail = "JOB_SUPPORT_EMAIL";

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(toEmail);
                message.setFrom(toEmail);
                message.setSubject("Job : " + jobName + " has failed");
                if (e != null) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw, true);
                    e.printStackTrace(pw);
                    pw.flush();
                    sw.flush();
                    message.setText(sw.toString(), true);
                }
            }
        };
        mailSender.send(preparator);
    }

    @Override
    public void sendBinClearReport(List<String> messages, final String emailAddress) {
        final StringBuffer emailBody = new StringBuffer();
        for (String message : messages) {
            emailBody.append(message).append("\n");
        }
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(emailAddress);
                message.setFrom("NO_REPLY_EMAIL");
                message.setSubject(getMessageSourceAccessor().getMessage("email.subject.bin.clear.report")
                        + DateFormatUtils.format(new Date(), "MM-dd-yyyy_HHmm"));
                message.setText(emailBody.toString(), false);
            }
        };

        mailSender.send(preparator);
    }

    @Override
    public void sendJobSuccessMessage(final String jobName, final String msg) {
    	final String toEmail = "JOB_SUPPORT_EMAIL";

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(toEmail);
                message.setFrom(toEmail);
                message.setSubject("Job : " + jobName + " has completed");
                if (msg != null) {
                    message.setText(msg, true);
                } else {
                    message.setText("Success!!", true);
                }
            }
        };

        mailSender.send(preparator);

    }

    @Override
    public void sendEmail(final String toEmail, final String fromEmail, final String subject, final String text,
            final EmailAttachmentBean... attachments) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                boolean multipart = false;
                if (attachments != null && attachments.length > 0) {
                    multipart = true;
                }
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, multipart);
                message.setTo(toEmail.split(","));
                message.setFrom(fromEmail);
                message.setSubject(subject);
                message.setText(text, true);
                if (attachments != null) {
                    for (EmailAttachmentBean attachment : attachments) {
                        if (attachment.getDataSource() != null) {
                            message.addAttachment(attachment.getFileName(), attachment.getDataSource());
                        } else {
                            DataSource dataSource = new ByteArrayDataSource(attachment.getAttachment(),
                                    attachment.getMimeType());
                            message.addAttachment(attachment.getFileName(), dataSource);
                        }
                    }
                }
            }
        };
        mailSender.send(preparator);
    }

    @Override
    public void sendASNItemFileErrorReport(List<String> messages, final String emailAddress, final String subject) {
        final StringBuffer emailBody = new StringBuffer();
        emailBody.append("<html><head></head><body>");
        for (String message : messages) {
            emailBody.append(message);
            emailBody.append("<br/>");
        }
        emailBody.append("</body></html>");
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(emailAddress);
                message.setFrom("NO_REPLY_EMAIL");
                message.setSubject(subject + " " + DateFormatUtils.format(new Date(), "MM-dd-yyyy_HHmm"));
                message.getMimeMessage().setHeader("Content-Type", "text/html");
                message.getMimeMessage().setContent(emailBody.toString(), "text/html");
            }
        };

        mailSender.send(preparator);
    }

}
