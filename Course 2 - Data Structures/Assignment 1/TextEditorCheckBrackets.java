import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
	Bracket(char type, int position) {
		this.type = type;
		this.position = position;
	}

	boolean Match(char c) {
		if (this.type == '[' && c == ']')
			return true;
		if (this.type == '{' && c == '}')
			return true;
		if (this.type == '(' && c == ')')
			return true;
		return false;
	}

	char type;
	int position;
}

/*  Input Format. Input contains one string 𝑆 which consists of big and small latin letters, digits, punctuation
    marks and brackets from the set []{}().

    Output Format. If the code in 𝑆 uses brackets correctly, output “Success" (without the quotes). Otherwise,
    output the 1-based index of the first unmatched closing bracket, and if there are no unmatched closing
    brackets, output the 1-based index of the first unmatched opening bracket.
*/

class TextEditorCheckBrackets {
	public static void main(String[] args) throws IOException {
		InputStreamReader input_stream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input_stream);
		String text = reader.readLine();
		int position = 0;
		Stack<Bracket> openingBracketsStack = new Stack<Bracket>();
		for ( position = 0; position < text.length(); ++position) {
			char next = text.charAt(position);

			if (next == '(' || next == '[' || next == '{') {
				Bracket bracket = new Bracket(next, position);
				openingBracketsStack.push(bracket);
			}

			if ((next == ')' || next == ']' || next == '}')) {
				if(openingBracketsStack.isEmpty()) {
					openingBracketsStack.push(new Bracket(next, position));
					break;
				}

				if(openingBracketsStack.peek().Match(next)) {
					openingBracketsStack.pop();
				}
				else {
					break;
				}
			}
		}

		if(openingBracketsStack.isEmpty()) {
			System.out.println("Success");
		}
		else if(position == text.length()) {
			System.out.println(openingBracketsStack.pop().position+1);
		}
		else {
			System.out.println(position+1);
		}
	}
}
