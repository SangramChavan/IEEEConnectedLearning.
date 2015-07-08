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
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import org.ieee.connected.SpringSystem;

public class ContainerView extends FrameLayout implements org.ieee.connected.SpringListener {
  private final SpringSystem mSpringSystem;
  private final org.ieee.connected.Spring mTransitionSpring;
  private Callback mCallback;

  public void clearCallback() {
    mCallback = null;
  }

  public interface Callback {
    void onProgress(double progress);
    void onEnd();
  }

  public ContainerView(Context context) {
    this(context, null);
  }

  public ContainerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ContainerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mSpringSystem = SpringSystem.create();
    mTransitionSpring = mSpringSystem.createSpring();
    setClickable(true);
    setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        mTransitionSpring.setCurrentValue(1).setAtRest();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
      }
    });
    setBackgroundColor(Color.WHITE);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    mTransitionSpring.addListener(this);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mTransitionSpring.removeListener(this);
  }

  public void reveal(boolean animated, Callback callback) {
    if (animated) {
      mTransitionSpring.setEndValue(0);
    } else {
      mTransitionSpring.setCurrentValue(0).setAtRest();
    }
    mCallback = callback;
  }

  public void hide(boolean animated, Callback callback) {
    if (animated) {
      mTransitionSpring.setEndValue(1);
    } else {
      mTransitionSpring.setCurrentValue(1).setAtRest();
    }
    mCallback = callback;
  }

  @Override
  public void onSpringUpdate(org.ieee.connected.Spring spring) {
    double val = spring.getCurrentValue();
    float xlat = (float) org.ieee.connected.SpringUtil.mapValueFromRangeToRange(val, 0, 1, 0, getWidth());
    setTranslationX(xlat);
    if (mCallback != null) {
      mCallback.onProgress(spring.getCurrentValue());
    }
  }

  @Override
  public void onSpringAtRest(org.ieee.connected.Spring spring) {
    if (mCallback != null) {
      mCallback.onEnd();
    }
  }

  @Override
  public void onSpringActivate(org.ieee.connected.Spring spring) {
  }

  @Override
  public void onSpringEndStateChange(org.ieee.connected.Spring spring) {
  }
}
