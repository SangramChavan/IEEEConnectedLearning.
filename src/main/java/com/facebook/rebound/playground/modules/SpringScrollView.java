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

package com.facebook.rebound.playground.modules;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.rebound.playground.R;
import com.facebook.rebound.playground.modules.scrollview.RowView1;


public class SpringScrollView extends FrameLayout {

    private final int ROW_COUNT = 25;

    public SpringScrollView(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.spring_scroll_view_example, this, false);
        ViewGroup content = (ViewGroup) root.findViewById(R.id.content_view);
        addView(root);
        ViewGroup contain = (ViewGroup) inflater.inflate(R.layout.why, this, false);
        addView(contain);
        setBackgroundColor(Color.argb(255, 50, 50, 50));
        int startColor = Color.argb(255,38, 41, 40);
        int endColor = Color.argb(255, 154, 161, 158);
        ArgbEvaluator evaluator = new ArgbEvaluator();
        for (int i = 0; i < ROW_COUNT; i++) {
            RowView1 exampleRowView = new RowView1(context);
            Integer color = (Integer) evaluator.evaluate((float) i / (float) ROW_COUNT, startColor, endColor);
            exampleRowView.setText("");
            exampleRowView.setBackgroundColor(color);
            content.addView(exampleRowView);

        }
    }

}