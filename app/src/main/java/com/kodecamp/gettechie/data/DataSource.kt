package com.kodecamp.gettechie

import com.kodecamp.gettechie.model.Course
import com.kodecamp.gettechie.model.Declaration


class DataSource {
    fun loadDelarations():List<Declaration>{
        return listOf<Declaration>(
            Declaration("Favour Smiles","UI/UX DESIGN",R.drawable.uiux),
            Declaration("OgoChuks","WEB DEVELOPMENT", R.drawable.web),
            Declaration("Imaobong John","DIGITAL MARKETING", R.drawable.digital),
            Declaration("Kayode Alao","BACKEND DEVELOPMENT", R.drawable.backend),
            Declaration("Imoleayo Bisiola","PYTHON", R.drawable.python),
            Declaration("Phemmyblaze","FRONTEND DEVELOPMENT", R.drawable.frontend),
            Declaration("Ikeys","ANDROID DEVELOPMENT", R.drawable.android),
            Declaration("Franklin John","MACHINE LEARNING", R.drawable.machine)

            )
    }
    fun loadDelarationstwo():List<Declaration>{
        return listOf<Declaration>(
            Declaration("Favour Smiles","PRODUCT MANAGEMENT",R.drawable.tproduct),
            Declaration("OgoChuks","FRONTEND DEVELOPMENT", R.drawable.tfront),
            Declaration("Imaobong John","DIGITAL MARKETING", R.drawable.tdigital),
            Declaration("Kayode Alao","BACKEND DEVELOPMENT", R.drawable.tback),
            Declaration("Imoleayo Bisiola","PYTHON", R.drawable.tpython),
            Declaration("Phemmyblaze","PROTOTYPING", R.drawable.tprototype),
            Declaration("Ikeys","ANDROID DEVELOPMENT", R.drawable.tandroid),
            Declaration("Franklin John","MACHINE LEARNING", R.drawable.tmachine)

        )
    }
    fun loadDelarationsthree():List<Declaration>{
        return listOf<Declaration>(
            Declaration("Favour Smiles","ANDROID DEVELOPMENT",R.drawable.randroid),
            Declaration("OgoChuks","COMPUTER AIDED DESIGN", R.drawable.rcomputer),
            Declaration("Imaobong John","DIGITAL MARKETING", R.drawable.tdigital),
            Declaration("Kayode Alao","BACKEND DEVELOPMENT", R.drawable.tback),
            Declaration("Imoleayo Bisiola","PYTHON", R.drawable.tpython),
            Declaration("Phemmyblaze","PROTOTYPING", R.drawable.tprototype),
            Declaration("Ikeys","PRODUCT MANAGEMENT", R.drawable.tandroid),
            Declaration("Franklin John","MACHINE LEARNING", R.drawable.tmachine)

        )
    }
    fun loadDelarationsfour():List<Declaration>{
        return listOf<Declaration>(
            Declaration("Favour Smiles","Session 1: Introduction",R.drawable.vone),
            Declaration("OgoChuks","Session 2: Development setup", R.drawable.vtwo),
            Declaration("Imaobong John","Session 3: Development setup 2", R.drawable.vthree),


        )
    }
    fun loadDelarationsfive():List<Declaration>{
        return listOf<Declaration>(
            Declaration("OgoChuks","session 1: Introduction", R.drawable.vfour),
            Declaration("Imaobong John","Session 2: Heading Elements and...", R.drawable.tdigital),
            Declaration("Kayode Alao","Session 3: HTML Characted Entity...", R.drawable.tback),
            Declaration("Imoleayo Bisiola","Session 4: HTML Lists", R.drawable.tpython),
            Declaration("Phemmyblaze","Session 5: HTML Links and images", R.drawable.tprototype),
            Declaration("Ikeys","Session Session 6: HTML 5 alignment and...", R.drawable.tandroid),
            Declaration("Franklin John","Session 7: HTML 5 Semantics", R.drawable.tmachine)

            )
    }
    fun loadDelarationswebdev():List<Course>{
        return listOf<Course>(
            Course("Session 1: Introduction"),
            Course("Session 2: Development setup"),
            Course("Session 3: Development setup 2")

        )
    }
    fun loadDelarationswebdevtwo():List<Course>{
        return listOf<Course>(
            Course("Session 1: Introduction"),
            Course("Session 2: Heading Elements and..."),
            Course("Session 3: HTML Characted Entity..."),
            Course("Session 4: HTML Lists"),
            Course("Session 5: HTML Links and images"),
            Course("Session Session 6: HTML 5 alignment and..."),
            Course("Session 7: HTML 5 Semantics")

        )
    }

    fun youtubeId():List<Course>{
        return listOf<Course>(
            Course("Dp9pHQ70NoI"),
            Course("v-oTdZ0rYMo"),
            Course("4zDaISf4Z10"),
            Course("MVZ9cFpHha4"),
            Course("9Lv1qRvYQWU"),
            Course("tVFUOjkKk98"),
            Course("P3stvZGWtZU")

        )
    }


}