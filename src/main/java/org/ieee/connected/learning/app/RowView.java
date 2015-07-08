/*
 * This file provided by Facebook is for non-commercial testing and evaluation purposes only.
 * Facebook reserves all rights not expressly granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * FACEBOOK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.ieee.connected.learning.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class RowView extends FrameLayout{
  private final TextView mTextView;
  private final TextView mSubTextView;

  public RowView(Context context) {
    this(context, null);
  }

  public RowView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RowView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(org.ieee.connected.learning.R.layout.row_view, this, false);
    mTextView = (TextView) view.findViewById(org.ieee.connected.learning.R.id.text_view);
    mSubTextView = (TextView) view.findViewById(org.ieee.connected.learning.R.id.subtext_view);
    setBackgroundResource(org.ieee.connected.learning.R.drawable.row_background);
    addView(view);
  }

  public void setText(String text) {
    mTextView.setText(text);
  }

  public void setSubtext(String text) {
    mSubTextView.setText(text);
  }
}
