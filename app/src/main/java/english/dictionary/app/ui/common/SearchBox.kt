package english.dictionary.app.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import english.dictionary.app.R
import english.dictionary.app.ui.theme.DefaultTextStyle
import english.dictionary.app.ui.theme.blue

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    label: String = "Search something",
    selectedStrokeColor: Color = blue,
    icon: Int = R.drawable.search,
    iconTint: Color = blue,
    textFieldValue: String,
    onTextFieldTextChanged: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    onKeyBoardOptionClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = textFieldValue,
            label = { Text(text = label, style = DefaultTextStyle(), color = Color.Gray) },
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "searchIcon",
                    tint = blue
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = {}),
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
            onValueChange = { onTextFieldTextChanged(it) })
    }
}