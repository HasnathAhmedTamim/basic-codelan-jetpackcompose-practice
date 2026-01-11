//kotlin
package com.example.basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme


/*
* Main activity class
* how it work :
* - It extends ComponentActivity and overrides the onCreate method.
* - Inside onCreate, it calls setContent to set the UI content of the activity using Jetpack Compose.
* - The UI content is wrapped in the BasicsCodelabTheme to apply the app's
* theme styles.
* - The MyApp Composable function is called with a Modifier that fills the maximum size of the screen.
*
* */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
/*
* App entry point Composable function
* How it work :
* - It uses a mutable state variable shouldShowOnboarding to track whether to show the onboarding screen or the greetings list.
* - Initially, shouldShowOnboarding is set to true, so the onboarding screen is displayed.
* - When the user clicks the "Continue" button on the onboarding screen, the onContinueClicked lambda is invoked, setting shouldShowOnboarding to false.
* - This causes a recomposition, and the Greetings Composable function is displayed instead.
*
* */
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}
/*
* App onboarding screen Composable function
* how it work :
* - It displays a welcome message and a "Continue" button.
* - The onContinueClicked parameter is a lambda function that is invoked when the "Continue" button is clicked.
* - The Column layout centers its content both vertically and horizontally within the available space.
* - The Text Composable displays the welcome message.
* - The Button Composable displays the "Continue" button, which triggers the onContinueClicked lambda when clicked.
* */
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        androidx.compose.material3.Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

/*
* Greetings list Composable function
* how it work :
* - It displays a scrollable list of greeting cards.
* - The LazyColumn Composable creates a vertically scrolling list.
* - The items function is used to generate a list item for each name in the names list.
* - Each item in the list is represented by a Greeting Composable function, which displays a greeting card for the given name.
* - The modifier parameter allows for additional modifications to the LazyColumn, such as padding.
* */

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

/*
* Greeting card Composable function
* how it work :
* - It displays a greeting card with a name.
* - The Card Composable creates a card UI element with a primary color background.
* - The modifier parameter allows for additional modifications to the Card, such as padding.
* - Inside the Card, the CardContent Composable function is called to display the content of
* */

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}


/*
* Card content Composable function
* how it work :
* - It displays the content of a greeting card, including the name and an expandable section.
* - The expanded state variable is used to track whether the card is expanded or collapsed.
* - The Row layout arranges the content horizontally, with padding and animated size changes.
* - The Column layout within the Row displays the greeting text and the expandable content.
* - The IconButton allows the user to toggle the expanded state by clicking the icon.
* */

@Composable
private fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

/*
* Greeting preview Composable functions
*how it work :
* - These functions provide previews of the Greetings Composable function in both light and dark themes.
* - The @Preview annotation is used to specify the preview settings, such as background visibility,
*   width, and UI mode (light or dark).
* - The GreetingPreview function wraps the Greetings Composable function in the BasicsCodelabTheme
*   to apply the app's theme styles.
* */

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}


/*
* onboarding screen preview Composable function
*how it work :
* - This function provides a preview of the OnboardingScreen Composable function.
* - The @Preview annotation is used to specify the preview settings, such as background visibility,
*   width, and height.
* - The OnboardingPreview function wraps the OnboardingScreen Composable function in the BasicsCodelabTheme
*   to apply the app's theme styles.
* */
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}


/*
* MyApp preview Composable function
*how it work :
* - This function provides a preview of the MyApp Composable function.
* - The @Preview annotation is used to specify the preview settings.
* - The MyAppPreview function wraps the MyApp Composable function in the BasicsCodelabTheme
*   to apply the app's theme styles.
* */

@Preview
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

/*
* project about :
* * A simple Android application built with Jetpack Compose that demonstrates basic UI components,
* * state management, and theming. The app features an onboarding screen and a list of greeting cards
* * that can be expanded to show more content.
*
* */
