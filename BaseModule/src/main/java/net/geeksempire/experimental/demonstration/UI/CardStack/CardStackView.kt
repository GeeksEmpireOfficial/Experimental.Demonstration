package net.geeksempire.experimental.demonstration.UI.CardStack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.card.stack.CardStackLayoutManager
import net.geeksempire.experimental.demonstration.UI.CardStack.Adapter.CardStackAdapter
import net.geeksempire.experimental.demonstration.databinding.CardStackViewBinding

class CardStackView : AppCompatActivity() {

    lateinit var cardStackViewBinding: CardStackViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardStackViewBinding = CardStackViewBinding.inflate(layoutInflater)
        setContentView(cardStackViewBinding.root)

        cardStackViewBinding.magazineCoverImage.layoutManager = CardStackLayoutManager(applicationContext)
        cardStackViewBinding.magazineCoverImage.adapter = CardStackAdapter()

    }

}