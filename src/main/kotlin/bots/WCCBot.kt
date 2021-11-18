package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class WCCBot : TelegramLongPollingBot() {

    val welcome = """
        Olá\, amigo\! Bem\-vindo ao meu JavaBot
        Digite o comando abaixo para dar início
        \/star \- para começar
        \/info \- para mais informações
    """.trimMargin()

    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "JavaBot"
    }

    override fun getBotToken(): String {
        // Return bot token from BotFather
        return "1957004094:AAHopzJN_Msee3-d3qW0oAx_8Nrxwj4Gzf4"
    }

    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName
        val chatId = update?.message?.chatId.toString()
        val messageCommand = update?.message?.text

        try {

            val sendDocument = SendDocument().apply {
                this.chatId = chatId
                this.caption = getMessage(messageCommand)
                this.document = InputFile().setMedia("https://media.giphy.com/media/SKGo6OYe24EBG/giphy.gif")
                this.parseMode = "MarkdownV2"
            }

            execute(sendDocument)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun getMessage(command: String?) = when(command) {
        "/info" -> "Não há informações"
        "/start" -> welcome
        else -> EmojiParser.parseToUnicode("tente novamente :cry:")
    }

    private fun sendMusicUpdate(update: Update?) {

    }
}

