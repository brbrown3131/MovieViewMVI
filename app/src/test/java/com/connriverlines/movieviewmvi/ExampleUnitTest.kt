
import com.connriverlines.movieviewmvi.Movie
import com.connriverlines.movieviewmvi.Utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val movie = Movie("Name", "Description", "http://test/test.png", "Category")
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun movieShouldInit() {
        assertNotNull(movie)
    }

    @Test
    fun utilsAdd_isCorrect() {
        val utils = Utils()
        assertEquals(4, utils.add(2,2))
    }
}