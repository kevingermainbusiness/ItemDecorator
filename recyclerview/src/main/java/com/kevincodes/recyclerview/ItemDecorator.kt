package com.kevincodes.recyclerview

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextPaint
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple utility class that helps you customize your [RecyclerView]'s
 * [ItemTouchHelper.SimpleCallback.onChildDraw] behavior.
 * It lets you add a background, an icon, a text, all of which you can
 * customize to a certain extent, as you swipe from any side of the [ItemTouchHelper].
 * The [ItemDecorator] properties will be provided by the [ItemDecorator.Builder] class.
 * @author Kevin Germain
 */
data class ItemDecorator(
    var canvas: Canvas, var recyclerView: RecyclerView, var viewHolder: RecyclerView.ViewHolder,
    var dX: Float, var actionState: Int
) {
    @ColorInt
    private var mBgColorFromStartToEnd = 0

    @ColorInt
    private var mBgColorFromEndToStart = 0

    @ColorInt
    private var mIconTintFromStartToEnd: Int? = null

    @ColorInt
    private var mIconTintFromEndToStart: Int? = null


    @ColorInt
    private var mTextColorFromStartToEnd = Color.DKGRAY

    @ColorInt
    private var mTextColorFromEndToStart = Color.DKGRAY

    @DrawableRes
    private var mIconResIdFromStartToEnd = 0

    @DrawableRes
    private var mIconResIdFromEndToStart = 0

    private var mTextFromStartToEnd: String? = null
    private var mTextFromEndToStart: String? = null
    private var mTypefaceFromStartToEnd = Typeface.SANS_SERIF
    private var mTypefaceFromEndToStart = Typeface.SANS_SERIF

    /* Default values */
    private var mDefaultIconHorizontalMargin = 0
    private var mDefaultTextSize = 14f
    private var mDefaultTextUnit = TypedValue.COMPLEX_UNIT_SP

    init {
        mDefaultIconHorizontalMargin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            16f,
            recyclerView.context.resources.displayMetrics
        ).toInt()
    }

    /**
     * Should only be called from a [RecyclerView]'s [ItemTouchHelper.SimpleCallback.onChildDraw]
     * callback method.
     * Each property inside this Builder class will be provided by the [RecyclerView]'s
     * [ItemTouchHelper.SimpleCallback.onChildDraw] callback method,
     * which will make passing data to the [ItemDecorator.decorate] method possible.
     * *************************************************************************
     * @param canvas The canvas which RecyclerView is drawing its children on.
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to.
     * @param viewHolder The ViewHolder which is being interacted by the User
     * or it was interacted and simply animating to its original position.
     * @param dX The amount of horizontal displacement caused by user's action.
     * @param actionState The type of interaction on the View,
     * It can either be an ACTION_STATE_DRAG or ACTION_STATE_SWIPE.
     * */
    class Builder(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        actionState: Int
    ) {
        private val mDecorator = ItemDecorator(canvas, recyclerView, viewHolder, dX, actionState)

        /**
         * Add a background color to both swiping directions
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of Builder
         */
        fun setDefaultBgColor(@ColorInt color: Int): Builder {
            mDecorator.mBgColorFromStartToEnd = color
            mDecorator.mBgColorFromEndToStart = color
            return this
        }

        /**
         * Add an action icon to both swiping directions
         * @param resourceId The resource id of the icon to be set
         * @return This instance of [Builder]
         */
        fun setDefaultIcon(@DrawableRes resourceId: Int): Builder {
            mDecorator.mIconResIdFromStartToEnd = resourceId
            mDecorator.mIconResIdFromEndToStart = resourceId
            return this
        }

        /**
         * Assigns a default tint color to both icons
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         * */
        fun setDefaultIconTintColor(@ColorInt color: Int): Builder {
            mDecorator.mIconTintFromStartToEnd = color
            mDecorator.mIconTintFromEndToStart = color
            return this
        }

        /**
         * Adds a default text for each side of the canvas
         * @param text default text for each side of the canvas
         * @return This instance of [Builder]
         * */
        fun setDefaultText(text: String?): Builder {
            mDecorator.mTextFromStartToEnd = text
            mDecorator.mTextFromEndToStart = text
            return this
        }

        /**
         * Adds a default color for the texts in each side of the canvas
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         * */
        fun setDefaultTextColor(@ColorInt color: Int): Builder {
            mDecorator.mTextColorFromStartToEnd = color
            mDecorator.mTextColorFromEndToStart = color
            return this
        }

        /**
         * Sets the size of the text to be shown while swiping in each side of the canvas
         * @param unit the unit to convert from, (e.g. [TypedValue.COMPLEX_UNIT_SP])
         * @param size the size to be set
         * @return This instance of [Builder]
         */
        fun setDefaultTextSize(unit: Int = TypedValue.COMPLEX_UNIT_SP, size: Float): Builder {
            mDecorator.mDefaultTextUnit = unit
            mDecorator.mDefaultTextSize = size
            return this
        }

        /**
         * Set the horizontal margin of the icon in the given unit (default is 16dp)
         * @param unit TypedValue, the unit to convert from, (e.g.[TypedValue.COMPLEX_UNIT_DIP])
         * @param iconHorizontalMargin the margin in the given unit
         *
         * @return This instance of [Builder]
         */
        fun setIconHorizontalMargin(unit: Int, iconHorizontalMargin: Int): Builder {
            mDecorator.mDefaultIconHorizontalMargin = TypedValue.applyDimension(
                unit,
                iconHorizontalMargin.toFloat(),
                mDecorator.recyclerView.context.resources.displayMetrics
            ).toInt()
            return this
        }

        /**
         * Adds a background color while swiping from start to end
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        fun setFromStartToEndBgColor(@ColorInt color: Int): Builder {
            mDecorator.mBgColorFromStartToEnd = color
            return this
        }

        /**
         * Adds a background color while swiping from end to start
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        fun setFromEndToStartBgColor(@ColorInt color: Int): Builder {
            mDecorator.mBgColorFromEndToStart = color
            return this
        }

        /**
         * Add an action icon while swiping from start to end
         * @param drawableId The resource id of the icon to be set
         * @return This instance of [Builder]
         */
        fun setFromStartToEndIcon(@DrawableRes drawableId: Int): Builder {
            mDecorator.mIconResIdFromStartToEnd = drawableId
            return this
        }

        /**
         * Add an action icon while swiping from end to start
         * @param drawableId The resource id of the icon to be set
         * @return This instance of [Builder]
         */
        fun setFromEndToStartIcon(@DrawableRes drawableId: Int): Builder {
            mDecorator.mIconResIdFromEndToStart = drawableId
            return this
        }

        /**
         * Set the tint color for the action icon shown while swiping from start to end
         * @param tintColor a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        fun setFromStartToEndIconTint(@ColorInt tintColor: Int): Builder {
            mDecorator.mIconTintFromStartToEnd = tintColor
            return this
        }

        /**
         * Set the tint color for the action icon shown while swiping from end to start
         * @param tintColor a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        fun setFromEndToStartIconTint(@ColorInt tintColor: Int): Builder {
            mDecorator.mIconTintFromEndToStart = tintColor
            return this
        }

        /**
         * Adds a text to be shown while swiping from start to end
         * @param text The string to be shown as text
         * @return This instance of [Builder]
         */
        fun setFromStartToEndText(text: String?): Builder {
            mDecorator.mTextFromStartToEnd = text
            return this
        }

        /**
         * Adds a text to be shown while swiping from end to start
         * @param text The string to be shown as text
         * @return This instance of [Builder]
         */
        fun setFromEndToStartText(text: String?): Builder {
            mDecorator.mTextFromEndToStart = text
            return this
        }

        /**
         * Sets the color of the text to be shown while swiping from start to end
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        fun setFromStartToEndTextColor(@ColorInt color: Int): Builder {
            mDecorator.mTextColorFromStartToEnd = color
            return this
        }

        /**
         * Set the color of the label to be shown while swiping from end to start
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        fun setFromEndToStartTextColor(@ColorInt color: Int): Builder {
            mDecorator.mTextColorFromEndToStart = color
            return this
        }

        /**
         * Sets the Typeface of the text to be shown while swiping from start to end
         * @param typeface the Typeface to be set (e.g. [Typeface.SANS_SERIF])
         * @return This instance of [Builder]
         */
        fun setFromStartToEndTypeface(typeface: Typeface): Builder {
            mDecorator.mTypefaceFromStartToEnd = typeface
            return this
        }

        /**
         * Sets the Typeface of the label to be shown while swiping from end to start
         * @param typeface the Typeface to be set (e.g. [Typeface.SANS_SERIF])
         * @return This instance of [Builder]
         */
        fun setFromEndToStartTypeface(typeface: Typeface): Builder {
            mDecorator.mTypefaceFromEndToStart = typeface
            return this
        }

        /**
         * @return The referenced [ItemDecorator] [mDecorator] in this [Builder] class
         */
        fun create(): ItemDecorator = mDecorator
    }

    /**
     * Assigns a tint color to an icon drawable
     * @param tintColor the tint color that will be assigned to the [Drawable]
     * */
    private fun Drawable.colorFilter(@ColorInt tintColor: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            this.setColorFilter(tintColor, PorterDuff.Mode.MULTIPLY)
        } else {
            this.colorFilter = BlendModeColorFilter(tintColor, BlendMode.SRC_ATOP)
        }
    }

    /**
     * Decorates an [RecyclerView.ViewHolder.itemView] as [ItemTouchHelper.LEFT]
     * or [ItemTouchHelper.RIGHT] are being fired,
     * with the dedicated backgrounds, texts and icons.
     *
     *  It does that by using the exact [recyclerView], [viewHolder], [dX] and [canvas]
     *  to draw on. These values are provided by its parent class [ItemDecorator], which itself
     *  gets the exact values from the [ItemDecorator.Builder] class.
     *  */
    fun decorate() {
        if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE) return
        if (dX > 0) fromStartToEndBehavior() else fromEndToStartBehavior()
    }

    /**
     * if [dX] > 0, that means that the user is swiping from the start of the
     * screen to the end of the screen (left to right).
     *
     * The [canvas] takes the size of the [viewHolder].
     * */
    private fun fromStartToEndBehavior() {
        canvas.clipRect(
            viewHolder.itemView.left,
            viewHolder.itemView.top,
            viewHolder.itemView.left + dX.toInt(),
            viewHolder.itemView.bottom
        )
        // Draws a color drawable on the canvas, with the same size as the canvas
        if (mBgColorFromStartToEnd != 0) {
            val cvBackgroundColor = ColorDrawable(mBgColorFromStartToEnd)
            cvBackgroundColor.bounds = canvas.clipBounds
            cvBackgroundColor.draw(canvas)
        }
        // Draws the icon contextualizing the swipe action
        var iconSize = 0
        if (mIconResIdFromStartToEnd != 0 && dX > mDefaultIconHorizontalMargin) {
            val icon = ContextCompat.getDrawable(
                recyclerView.context, mIconResIdFromStartToEnd
            )
            icon?.let {
                iconSize = it.intrinsicHeight
                val halfIcon = iconSize / 2
                val top =
                    viewHolder.itemView.top +
                            ((viewHolder.itemView.bottom - viewHolder.itemView.top) / 2 - halfIcon)
                it.setBounds(
                    viewHolder.itemView.left + mDefaultIconHorizontalMargin,
                    top,
                    viewHolder.itemView.left + mDefaultIconHorizontalMargin + it.intrinsicWidth,
                    top + iconSize
                )
                mIconTintFromStartToEnd?.let { iconTintColor ->
                    it.colorFilter(iconTintColor)
                }
                it.draw(canvas)
            }
        }
        // Draws the descriptive text contextualizing the swipe action
        mTextFromStartToEnd?.let {
            if (dX > mDefaultIconHorizontalMargin + iconSize) {
                val textPaint = TextPaint()
                textPaint.isAntiAlias = true
                textPaint.textSize = TypedValue.applyDimension(
                    mDefaultTextUnit,
                    mDefaultTextSize,
                    recyclerView.context.resources.displayMetrics
                )
                textPaint.color = mTextColorFromStartToEnd
                textPaint.typeface = mTypefaceFromStartToEnd
                val textTop =
                    (viewHolder.itemView.top + (viewHolder.itemView.bottom - viewHolder.itemView.top) / 2.0 + textPaint.textSize / 2).toInt()
                canvas.drawText(
                    it,
                    viewHolder.itemView.left + mDefaultIconHorizontalMargin + iconSize + (if (iconSize > 0) mDefaultIconHorizontalMargin / 2 else 0).toFloat(),
                    textTop.toFloat(),
                    textPaint
                )
            }
        }
    }

    /**
     * if [dX] < 0, that means that the user is swiping from the end of the
     * screen to the start of the screen (right to left).
     *
     * The [canvas] takes the size of the [viewHolder].
     * */
    private fun fromEndToStartBehavior() {
        canvas.clipRect(
            viewHolder.itemView.right + dX.toInt(),
            viewHolder.itemView.top,
            viewHolder.itemView.right,
            viewHolder.itemView.bottom
        )
        // Draws a color drawable on the canvas, with the same size as the canvas
        if (mBgColorFromEndToStart != 0) {
            val cvBackgroundColor = ColorDrawable(mBgColorFromEndToStart)
            cvBackgroundColor.bounds = canvas.clipBounds
            cvBackgroundColor.draw(canvas)
        }
        // Draws the icon contextualizing the swipe action
        var imgEnd = viewHolder.itemView.right
        var iconSize = 0
        if (mIconResIdFromEndToStart != 0 && dX < -mDefaultIconHorizontalMargin) {
            val icon = ContextCompat.getDrawable(
                recyclerView.context, mIconResIdFromEndToStart
            )
            icon?.let {
                iconSize = it.intrinsicHeight
                val halfIcon = iconSize / 2
                val top =
                    viewHolder.itemView.top +
                            ((viewHolder.itemView.bottom - viewHolder.itemView.top) / 2 - halfIcon)
                imgEnd = viewHolder.itemView.right - mDefaultIconHorizontalMargin - halfIcon * 2
                it.setBounds(
                    imgEnd,
                    top,
                    viewHolder.itemView.right - mDefaultIconHorizontalMargin,
                    top + it.intrinsicHeight
                )
                mIconTintFromEndToStart?.let { iconTintColor ->
                    it.colorFilter(iconTintColor)
                }
                it.draw(canvas)
            }
        }
        // Draws the descriptive text contextualizing the swipe action
        mTextFromEndToStart?.let {
            if (dX < -mDefaultIconHorizontalMargin - iconSize) {
                val textPaint = TextPaint()
                textPaint.isAntiAlias = true
                textPaint.textSize = TypedValue.applyDimension(
                    mDefaultTextUnit,
                    mDefaultTextSize,
                    recyclerView.context.resources.displayMetrics
                )
                textPaint.color = mTextColorFromEndToStart
                textPaint.typeface = mTypefaceFromEndToStart
                val width = textPaint.measureText(it)
                val textTop =
                    (viewHolder.itemView.top + (viewHolder.itemView.bottom - viewHolder.itemView.top) / 2.0 + textPaint.textSize / 2.0).toInt()

                canvas.drawText(
                    it,
                    imgEnd - width - if (imgEnd == viewHolder.itemView.right) mDefaultIconHorizontalMargin else mDefaultIconHorizontalMargin / 2,
                    textTop.toFloat(),
                    textPaint
                )
            }
        }
    }
}