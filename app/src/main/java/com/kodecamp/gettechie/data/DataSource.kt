package com.kodecamp.gettechie

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
}